package chatClient;

/* Coyright Eric Cariou, 2009 - 2011 */

import communication.CommunicationException;
import communication.FaultLevel;
import communication.Message;
import communication.ReliabilitySetting;

import service.ICommunication;
import service.DistributedServicesMiddleware;
import service.IDistributedServices;
import service.IIdentification;
import service.IBroadcast;

public class Chat extends Thread {

    IDistributedServices services;
    ICommunication commService;
    IIdentification idService;
    IBroadcast broadcastService;

    public void init() {

        // setting of the simulated system
        ReliabilitySetting setting = new ReliabilitySetting();
        setting.setTransmissionDelayLowerBound(FaultLevel.NONE);
        setting.setTransmissionDelayUpperBound(FaultLevel.NONE);
        setting.setPacketLostLevel(FaultLevel.NONE);
        setting.setCrashLevel(FaultLevel.NONE);
        setting.setReliable(false);
        setting.setDebugFault(true);

        // connection to the system
        services = new DistributedServicesMiddleware();
        try {
            services.connect(setting);
        } catch (CommunicationException e) {
            System.err.println("Impossible de se connecter : " + e);
        }
        // get the service access points
        commService = services.getCommunicationService();
        idService = services.getIdentificationService();
        broadcastService = services.getReliableBroadcastService();
        broadcastService.initCheckBuffer();

        // as we are not directly informed when the process id has been received, wait a short time
        // to be almost sure to have received it when printing the identifier
        try { Thread.sleep(200); } catch(Exception e) { }
        System.out.println("OK, connexion réalisée, je suis : " + idService.getMyIdentifier()+ "\n");
    }
 
    public void sendMessage(String text,String sender,String recepientGroup){
        //if end, disconnect from the system and exit the JVM
        if(text.equals("end")){
            System.out.println("A la prochaine !");
            services.disconnect();
            System.exit(0);
        }
        //broadcast the message
        try{
            System.out.print("--> Envoi message...");
            Message msg = new Message(idService.getMyIdentifier(),text,sender,recepientGroup);
            System.out.println(msg.getRecepientGroup());
            broadcastService.broadcast(msg);
            System.out.println("Message envoyé");
        }catch (CommunicationException ex){
            System.err.println("*** Probleme de communication: "+ ex);
        }
    }

    @Override
    public void run() {
        // wait in an infinite loop for a message to be received
        Message msg;
        while (true) {
            msg = broadcastService.synchDeliver();
            System.out.println("[" + msg.getProcessId().getId() + "] " + msg.getData());
       
        }
    }

    public void Chat() {}
}
