package Chat;

/**
* Chat/ChatServerSvcsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Chat.idl
* Wednesday, April 13, 2016 6:17:13 PM EDT
*/

public final class ChatServerSvcsHolder implements org.omg.CORBA.portable.Streamable
{
  public Chat.ChatServerSvcs value = null;

  public ChatServerSvcsHolder ()
  {
  }

  public ChatServerSvcsHolder (Chat.ChatServerSvcs initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Chat.ChatServerSvcsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Chat.ChatServerSvcsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Chat.ChatServerSvcsHelper.type ();
  }

}
