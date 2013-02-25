/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global.filehandling;

/**
 *
 * @author karlinsv
 */
class FileHandlingMessage {

    public enum FileHandlingMessageCode {
        SUCCESS, FILE_SEND_ERROR, FILE_RECEIVE_ERROR
    }
    
    private Object sender;
    private FileHandlingMessageCode code;
    private String message;

    public FileHandlingMessageCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getSender() {
        return sender;
    }
    
    public FileHandlingMessage(Object sender, FileHandlingMessageCode code, String message) {
        this.sender = sender;
        this.code = code;
        this.message = message;
    }
    
}
