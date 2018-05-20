package Chat;


/**
* Chat/TimeVectorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Chat.idl
* Wednesday, April 13, 2016 6:17:13 PM EDT
*/

abstract public class TimeVectorHelper
{
  private static String  _id = "IDL:Chat/TimeVector:1.0";

  public static void insert (org.omg.CORBA.Any a, int[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static int[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
      __typeCode = org.omg.CORBA.ORB.init ().create_array_tc (Chat.MAX_NUM_PROCESSES.value, __typeCode );
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (Chat.TimeVectorHelper.id (), "TimeVector", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static int[] read (org.omg.CORBA.portable.InputStream istream)
  {
    int value[] = null;
    value = new int[Chat.MAX_NUM_PROCESSES.value];
    for (int _o0 = 0;_o0 < (Chat.MAX_NUM_PROCESSES.value); ++_o0)
    {
      value[_o0] = istream.read_long ();
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, int[] value)
  {
    if (value.length != (Chat.MAX_NUM_PROCESSES.value))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    for (int _i0 = 0;_i0 < (Chat.MAX_NUM_PROCESSES.value); ++_i0)
    {
      ostream.write_long (value[_i0]);
    }
  }

}