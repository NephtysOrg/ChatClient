/* Coyright Eric Cariou, 2009 - 2011 */

package communication;

import java.util.Random;

/**
 * Messages are sent and received by processes. A message contains two fields: the identifier
 * of the sender (or receiver) and the data to send (or being received). Data is simply any
 * kind of Java object (that must nevertheless be serializable).
 */
public class Message implements java.io.Serializable {
    /**
     * The sender of this message
     */
    protected String sender;
    
    /**
     * The group of users we will receive this message
     */
    protected String recepientGroup;
    
    /**
     * the single identifier of this message
     */
    protected String singleId;

    /**
     * The identifier of the process that either sent the message or either is the
     * receiver of the message
     */
    protected ProcessIdentifier processId;
    /**
     * Data embedded in the message
     */
    protected Object data;

    /**
     * @return the process identifier embedded in the message
     */
    public ProcessIdentifier getProcessId() {
        return processId;
    }

    /**
     * @param processId the process identifier to set
     */
    public void setProcessId(ProcessIdentifier processId) {
        this.processId = processId;
    }

    /**
     * @return the data of the message
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @param processId the process identifier for the message
     * @param data the data of the message
     */
    public Message(ProcessIdentifier processId, Object data) {
        super();
        this.processId = processId;
        this.data = data;
    }
    
    /**
     * @param processId the process identifier for the message
     * @param data the data of the message
     * @param sender
     * @param recepientGroup
     */
    public Message(ProcessIdentifier processId,Object data,String sender,String recepientGroup){
        super();
        this.processId = processId;
        this.data = data;
        this.singleId = processId.getId() + String.valueOf(new Random().nextInt(1000));
        this.sender = sender;
        this.recepientGroup = recepientGroup;
    }

    @Override
    public String toString() {
        return "[ " + processId + " ] -> " + data;
    }
    
    //@Override
    /*public String toString(){
        return "[" + processId +"|"+sender+"|"+recepientGroup+"]->"+data;
    }*/
    
    public String getSender() {
        return sender;
    }

    public String getRecepientGroup() {
        return recepientGroup;
    }

    public String getSingleId() {
        return singleId;
    }
}
