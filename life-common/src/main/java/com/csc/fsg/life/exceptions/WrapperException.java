package com.csc.fsg.life.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.csc.fsg.life.common.util.TagHelper;


/**
 * Wraps and converts non {@link java.lang.RuntimeException}s to
 * {@link java.lang.RuntimeException} s. In addition, it is capable of realizing
 * provided message code and adding the message to the wrapped exception. All
 * wrapped message codes are cached and can be queried to see if a particular
 * message code exists.
 */
@SuppressWarnings("unchecked")
public class WrapperException 
	extends RuntimeException
{
    // ~ Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Wraps and returns non-RuntimeExceptions in WrapperException;
     * RuntimeExceptions are returned unaltered.
     */
	public static RuntimeException wrap(Throwable t)
    {
        Class weClass = null;
        String msgCode = null;
        Object[] msgParts = null;

        return wrap(t, weClass, msgCode, msgParts);
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, Class weClass)
    {
        String msgCode = null;
        Object[] msgParts = null;

        return wrap(t, weClass, msgCode, msgParts);
    }

    /**
     * Convenience method for {@link #wrap(Throwable, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, String msgCode)
    {
        Class weClass = null;
        Object[] msgParts = null;

        return wrap(t, weClass, msgCode, msgParts);
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, Class weClass, String msgCode)
    {
        Object[] msgParts = null;

        return wrap(t, weClass, msgCode, msgParts);
    }

    /**
     * Convenience method for {@link #wrap(Throwable, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, String msgCode, Object msgPart1)
    {
        Class weClass = null;

        return wrap(t, weClass, msgCode, new Object[]{msgPart1});
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, Class weClass, String msgCode, Object msgPart1)
    {
        return wrap(t, weClass, msgCode, new Object[]{msgPart1});
    }

    /**
     * Convenience method for {@link #wrap(Throwable, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, String msgCode, Object msgPart1,
        Object msgPart2)
    {
        Class weClass = null;

        return wrap(t, weClass, msgCode, new Object[]{msgPart1, msgPart2});
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, Class weClass, String msgCode,
        Object msgPart1, Object msgPart2)
    {
        return wrap(t, weClass, msgCode, new Object[]{msgPart1, msgPart2});
    }

    /**
     * Convenience method for {@link #wrap(Throwable, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, String msgCode, Object msgPart1,
        Object msgPart2, Object msgPart3)
    {
        Class weClass = null;

        return wrap(t, weClass, msgCode, new Object[]{msgPart1, msgPart2, msgPart3});
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, Class weClass, String msgCode,
        Object msgPart1, Object msgPart2, Object msgPart3)
    {
        return wrap(t, weClass, msgCode, new Object[]{msgPart1, msgPart2, msgPart3});
    }

    /**
	   Convenience method for {@link #wrap(Throwable, Class, String, Object[])}.
     */
    public static RuntimeException wrap(Throwable t, String msgCode, Object[] msgParts)
    {
        Class clazz = null;

        return wrap(t, clazz, msgCode, msgParts);
    }

    /**
     * Wraps and returns the provided throwable with a formatted message built
     * from the provided message code and parts.
     * 
     * @throws WrapperException
     *             When a formatted message cannot be built.
     */
    public static RuntimeException wrap(Throwable t, Class weClass, String msgCode,
        Object[] msgParts)
    {
        if (t instanceof WrapperException)
        {
            WrapperException we = (WrapperException) t;

            we.prependMessage(msgCode, msgParts);

            return we;
        }

        if (t instanceof InvocationTargetException)
            t = ((InvocationTargetException) t).getTargetException();

        // Commented to allow this class to be used in the client.
        // if (t instanceof ServletException)
        //     t = ((ServletException) t).getRootCause();

        List<Throwable> hierarchy = new ArrayList<Throwable>();
        t = stripAndDecorateRootException(t, hierarchy);
        return newWrapperException(t, weClass, msgCode, msgParts);
    }

    /**
     * This method strips the wrapped exceptions and decorates the root
     * exception simply with the messages from the wrapped exceptions using
     * {@link WrapperException}.
     */
    private static Throwable stripAndDecorateRootException(Throwable t, List<Throwable> hierarchy)
    {
        collectExceptionHierarchy(t, hierarchy);
        int size = hierarchy.size();
        Throwable eWrapped = hierarchy.get(size - 1);
        for (int i = size - 2; i > -1; i--) {
            t = hierarchy.get(i);
            eWrapped = wrap(eWrapped, t.toString());
        }

        return eWrapped;
    }

    /**
     * Recursive method that unwraps and collects the nested exceptions in the
     * outer-to-inner order. For example, if the given exception is of the form
     * <code>new Exception("", new SecurityException(""))</code> then the
     * final hierarchy list will contain the exceptions in the following order:
     * [Exception, SecurityException].
     */
    private static void collectExceptionHierarchy(Throwable t, final List<Throwable> hierarchy)
    {
        hierarchy.add(t);

        if (t instanceof WrapperException || t.getCause() == null)
            return;

        collectExceptionHierarchy(t.getCause(), hierarchy);
    }

    private static RuntimeException newWrapperException(Throwable t, Class weClass, String msgCode,
        Object[] msgParts)
    {
        if (weClass == null || !WrapperException.class.isAssignableFrom(weClass))
            return new WrapperException(t, msgCode, msgParts);

        try {
            Constructor ctor = weClass.getDeclaredConstructor(new Class[]{Throwable.class,
                String.class, Object[].class});

            return (WrapperException) ctor.newInstance(new Object[]{t, msgCode, msgParts});
        }
        catch (Exception e)
        {
            return new WrapperException(t, msgCode, msgParts);
        }
    }

    // ~ Instance Variables
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private String _msg;
    private Throwable _wrapped;
    private List<String> _msgCodes = new ArrayList<String>();

    // ~ Constructors
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected WrapperException(Throwable t, String msgCode, Object[] msgParts)
    {
        // Wrapped is accessed within formatMessage so it needs to be set prior
        // to making the call.
        _wrapped = t;

        if (msgCode != null)
            _msg = formatMessage(msgCode, msgParts);
    }

    private String formatMessage(String msgCode, Object[] msgParts)
    {
        if (msgCode == null)
            return null;
        
        try {
            _msgCodes.add(msgCode);

            return Message.formatSpecial(msgCode, msgParts);
        }
        catch (Exception e) {
            _msg = "Unable to format message for code: " + msgCode + "\n\tWhile throwing: "
                + getCause();
            _wrapped = e;

            throw this;
        }
    }

    private void prependMessage(String msgCode, Object[] msgParts)
    {
        String msg = formatMessage(msgCode, msgParts);

        _msg = (_msg == null) ? msg : (msg + "\n\t" + _msg);
    }

    // ~ Inherited Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Returns the message for this and all wrapped exceptions.
     */
    public String getMessage()
    {
        return _msg == null ? _wrapped.getMessage() : _msg;
    }

    /**
     * @return Returns {@link #getMessage()}.
     */
    public String toString()
    {
        return getMessage();
    }

    public void printStackTrace()
    {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream out)
    {
        synchronized (out)
        {
            if (_msg != null)
                out.println(this);

            _wrapped.printStackTrace(out);
        }
    }

    public void printStackTrace(PrintWriter out)
    {
        synchronized (out)
        {
            if (_msg != null)
                out.println(TagHelper.normalize(this.toString()));

            _wrapped.printStackTrace(out);
        }
    }

    /**
     * @return Returns the root cause that is wrapped in this exception.
     */
    public Throwable getCause()
    {
        return _wrapped;
    }

    /**
     * @return true, if the provided message code IS wrapped in this exception.
     * @return false, if the provided message code is NOT wrapped in this
     *         exception.
     */
    public boolean isMessageCode(String msgCode)
    {
        return _msgCodes.contains(msgCode);
    }
}
