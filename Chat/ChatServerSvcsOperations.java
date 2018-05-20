package Chat;


/**
* Chat/ChatServerSvcsOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Chat.idl
* Wednesday, April 13, 2016 6:17:13 PM EDT
*/

public interface ChatServerSvcsOperations 
{
  int getNextId ();
  void register (Chat.ChatClientSvcs clientRef) throws Chat.CannotEstablishConnection, Chat.DuplicateClient;
  void unRegister (Chat.ChatClientSvcs clientRef) throws Chat.UnknownClient;
  void sendMsg (int[] t, Chat.ChatClientSvcs clientRef, String msg, int myId) throws Chat.UnknownClient;
} // interface ChatServerSvcsOperations