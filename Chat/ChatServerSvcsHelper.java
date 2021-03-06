package Chat;


/**
* Chat/ChatServerSvcsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Chat.idl
* Wednesday, April 13, 2016 6:17:13 PM EDT
*/

abstract public class ChatServerSvcsHelper
{
  private static String  _id = "IDL:Chat/ChatServerSvcs:1.0";

  public static void insert (org.omg.CORBA.Any a, Chat.ChatServerSvcs that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Chat.ChatServerSvcs extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Chat.ChatServerSvcsHelper.id (), "ChatServerSvcs");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Chat.ChatServerSvcs read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ChatServerSvcsStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Chat.ChatServerSvcs value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Chat.ChatServerSvcs narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Chat.ChatServerSvcs)
      return (Chat.ChatServerSvcs)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Chat._ChatServerSvcsStub stub = new Chat._ChatServerSvcsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Chat.ChatServerSvcs unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Chat.ChatServerSvcs)
      return (Chat.ChatServerSvcs)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Chat._ChatServerSvcsStub stub = new Chat._ChatServerSvcsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
