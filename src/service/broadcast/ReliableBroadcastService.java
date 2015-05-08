/* Coyright Eric Cariou, 2009 - 2011 */
package service.broadcast;

import java.util.Iterator;

import communication.CommunicationException;
import communication.CompoundException;
import communication.Message;
import communication.ProcessIdentifier;
import communication.SynchronizedBuffer;
import service.IBroadcast;
import service.IIdentification;
import service.MessageType;
import service.Service;
import service.TypedMessage;

public class ReliableBroadcastService extends Service implements IBroadcast {

    private java.util.Vector<Object> list = new java.util.Vector<Object>();
    private SynchronizedBuffer<Message> buffMsgToDeliver = new SynchronizedBuffer<Message>();
    private CheckBuffer _checkBuffer;
    protected IIdentification idService;

    public class CheckBuffer extends Thread {

        @Override
        public void run() {
            while (true) {
                Message m = buffer.removeElement(true);
                if (m.getData() instanceof Message) {
                    Message msg = (Message) m.getData();
                    System.out.println("Message reçu : " + msg.getData());  
                    if (!list.contains(msg.getSingleId())) {
                        //System.out.println("Nouveau message => Broadcast");
                        buffMsgToDeliver.addElement(msg);
                        //System.out.println("Taille buffMsgToDeliver : " + buffMsgToDeliver.available());
                        try {
                            broadcast(msg);
                        } catch (CommunicationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        //System.out.println("Message non nouveau");
                    }
                    list.add(msg.getSingleId());
                }
            }
        }
    }

    @Override
    public void initCheckBuffer() {
        this._checkBuffer = new CheckBuffer();
        this._checkBuffer.start();
    }

    @Override
    public void broadcast(Object data) throws CommunicationException {
        System.out.println("----------------------------------------");
        ProcessIdentifier id;
        Iterator it;
        CompoundException exceptions = null;
        CommunicationException firstException = null;
        it = idService.getAllIdentifiers().iterator();
        // send the data to all the processes
        while (it.hasNext()) {
            id = (ProcessIdentifier) it.next();
            try {
                // simulate the crash of the process during the broadcast
                commElt.crashProcess();
                /*
                 * IMPORTANT : Il y a creation a chaque fois d'un nouveau message ce qui fait
                 * qu'une methode random dans le contructeur de message NE MARCHE PAS
                 */
                commElt.sendMessage(new TypedMessage(id, data, MessageType.RELIABLE_BROADCAST));
                // My add
                if (data instanceof Message) {
                    list.add(((Message) data).getSingleId());
                }
            } catch (CommunicationException e) {
                if (firstException == null) {
                    firstException = e;
                } else {
                    if (exceptions == null) {
                        exceptions = new CompoundException();
                        exceptions.addException(firstException);
                    }
                    exceptions.addException(e);
                }
            }
        }
        if (exceptions != null) {
            throw exceptions;
        }
        if (firstException != null) {
            throw firstException;
        }
    }

    @Override
    public Message synchDeliver() {
        //return buffer.removeElement(true);
        //System.out.println("Taille buffMsgToDeliver : " + buffMsgToDeliver.available());
        return buffMsgToDeliver.removeElement(true);
    }

    @Override
    public Message asynchDeliver() {
        return buffMsgToDeliver.removeElement(false);
    }

    @Override
    public boolean availableMessage() {
        return buffer.available() > 0;
    }

    public void setIdentificationService(IIdentification idService) {
        this.idService = idService;
    }
}

