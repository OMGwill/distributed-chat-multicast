package Chat;


/**
* Chat/_ChatServerSvcsStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Chat.idl
* Wednesday, April 13, 2016 6:17:13 PM EDT
*/

public class _ChatServerSvcsStub extends org.omg.CORBA.portable.ObjectImpl implements Chat.ChatServerSvcs
{

  public int getNextId ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getNextId", true);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getNextId (        );
            } finally {
                _releaseReply ($in);
            }
  } // getNextId

  public void register (Chat.ChatClientSvcs clientRef) throws Chat.CannotEstablishConnection, Chat.DuplicateClient
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("register", true);
                Chat.ChatClientSvcsHelper.write ($out, clientRef);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Chat/CannotEstablishConnection:1.0"))
                    throw Chat.CannotEstablishConnectionHelper.read ($in);
                else if (_id.equals ("IDL:Chat/DuplicateClient:1.0"))
                    throw Chat.DuplicateClientHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                register (clientRef        );
            } finally {
                _releaseReply ($in);
            }
  } // register

  public void unRegister (Chat.ChatClientSvcs clientRef) throws Chat.UnknownClient
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unRegister", true);
                Chat.ChatClientSvcsHelper.write ($out, clientRef);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Chat/UnknownClient:1.0"))
                    throw Chat.UnknownClientHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                unRegister (clientRef        );
            } finally {
                _releaseReply ($in);
            }
  } // unRegister

  public void sendMsg (int[] t, Chat.ChatClientSvcs clientRef, String msg, int myId) throws Chat.UnknownClient
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMsg", true);
                Chat.TimeVectorHelper.write ($out, t);
                Chat.ChatClientSvcsHelper.write ($out, clientRef);
                $out.write_string (msg);
                $out.write_long (myId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:Chat/UnknownClient:1.0"))
                    throw Chat.UnknownClientHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendMsg (t, clientRef, msg, myId        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMsg

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Chat/ChatServerSvcs:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ChatServerSvcsStub