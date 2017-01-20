package com.csc.fsg.life.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.HtmlUtils;

/* Modifications: T0106 */

/**
 * This is a utility class designed to facilitate basic sanitization of String
 * contents.<br>
 * At high level it provides the following types of functionality:
 * <ul>
 * <li>
 * <b>Plain text sanitization:</b> Replace individual line breaks or series of
 * consecutive line breaks, in this class collectively referred to as
 * blacklisted contents, with a space character.</li>
 * <li>
 * <b>HTML text sanitization:</b> Optionally replace all HTML special characters
 * with their corresponding entity references. Then replace individual line
 * breaks or series of consecutive line breaks, in this class collectively
 * referred to as blacklisted contents, with the HTML tag &lt;br&gt;. Note that
 * the more strict form &lt;br/&gt; is not used, because in many cases the tag
 * &lt;br&gt; is parsed at application level in <i>pw-client</i>, using logic,
 * which expects this specific format.</li>
 * <li>
 * <b>HTML text escaping:</b> Replace all HTML special characters, with possible
 * exception of characters in a non-breaking space entity
 * <code>&amp;nbsp;</code>, with their corresponding entity references.</li>
 * <li>
 * <b>URL text escaping:</b> URL-encode all characters.</li>
 * </ul>
 */
abstract public class OutputSanitizer
{
	// Blacklisted content definition and sanitization support
	private static final String NEW_LINE_CHARACTERS = "([\\r\\n\\f])+";
	private static final String DEFAULT_REPLACEMENT = " ";
	private static final String HTML_BR_REPLACEMENT = "<br>";

	// Non-breaking space definition
	private static final String NB_SPACE = "&nbsp;";
	private static final int NB_SPACE_LENGTH = NB_SPACE.length();

	/**
	 * Replace blacklisted contents with spaces in plain text input.
	 * 
	 * @param plainText
	 *        Input text to be sanitized.
	 * @return The input text with each instance of blacklisted contents
	 *         replaced by a space.
	 */
	public static String sanitizePlainText(String plainText)
	{
		return removeBlacklistedContents(plainText, NEW_LINE_CHARACTERS, DEFAULT_REPLACEMENT);
	}

	/**
	 * Replace blacklisted contents with html tag &lt;br&gt; in HTML text input.
	 * 
	 * @param htmlText
	 *        Input HTML text to be sanitized.
	 * @return The input text with each instance of blacklisted contents
	 *         replaced by the html tag &lt;br&gt;.
	 * @see #escapeHtmlText(String, boolean)
	 */
	public static String sanitizeHtmlText(String htmlText)
	{
		return sanitizeHtmlText(htmlText, false);
	}

	/**
	 * Optionally replace special HTML characters with entity references, and
	 * then replace blacklisted contents with html tag &lt;br&gt; in HTML text
	 * input.
	 * 
	 * @param htmlText
	 *        Input HTML text to be sanitized.
	 * @param isEscapingRequired
	 *        A flag indicating whether special characters are to be replaced by
	 *        entity references.
	 * @return The input text with special characters optionally replaced by
	 *         entity references, and each instance of blacklisted contents
	 *         replaced by the html tag &lt;br&gt;.
	 * @see #escapeHtmlText(String)
	 */
	public static String sanitizeHtmlText(String htmlText, boolean isEscapingRequired)
	{
		String text = htmlText;
		if (isEscapingRequired)
			text = escapeHtmlText(text);

		return removeBlacklistedContents(text, NEW_LINE_CHARACTERS, HTML_BR_REPLACEMENT);
	}

	/**
	 * Replace special HTML characters with entity references.
	 * <p>
	 * 
	 * @param htmlText
	 *        Input HTML text to be escaped.
	 * @return The input text with special characters replaced by entity
	 *         references.
	 * @see #escapeHtmlText(String, boolean)
	 */
	public static String escapeHtmlText(String htmlText)
	{
		return escapeHtmlText(htmlText, true);
	}

	/**
	 * Replace special HTML characters with entity references.
	 * <p>
	 * A possible exception may be the non-breaking space entity
	 * <code>&amp;nbsp;</code>, which is frequently used in lieu of actual data,
	 * for example to indicate an empty HTML table cell; this sequence of
	 * characters will not be escaped. This exception can be triggered by
	 * specifying <code>false</code> value <code>isFullEscape</code>.
	 * 
	 * @param htmlText
	 *        Input HTML text to be escaped.
	 * @param isFullEscape
	 *        If <code>true</code>, then all characters are escaped; otherwise
	 *        <code>&amp;nbsp;</code> is left unescaped.
	 * @return The input text with special characters replaced by entity
	 *         references.
	 * @see #escapeHtmlText(String)
	 */
	public static String escapeHtmlText(String htmlText, boolean isFullEscape)
	{
		if (htmlText == null)
			return null;
		
		if (isFullEscape)
			return HtmlUtils.htmlEscape(htmlText);

		int len = htmlText.length();
		StringBuilder source = new StringBuilder(htmlText);
		StringBuilder target = new StringBuilder(len);
		StringBuilder collector = new StringBuilder();

		while (source.length() > 0) {
			if (source.length() >= NB_SPACE_LENGTH 
			 && source.substring(0, NB_SPACE_LENGTH).equals(NB_SPACE)) {
				if (collector.length() > 0) {
					target.append(HtmlUtils.htmlEscape(collector.toString()));
					collector.setLength(0);
				}
				target.append(NB_SPACE);
				source.delete(0, NB_SPACE_LENGTH);
			}
			else {
				collector.append(source.charAt(0));
				source.deleteCharAt(0);
			}
		}

		if (collector.length() > 0)
			target.append(HtmlUtils.htmlEscape(collector.toString()));

		return target.toString();
	}

	/**
	 * URL-encode the provided text.
	 * 
	 * @param urlText
	 *        Input URL text to be escaped.
	 * @return The input text with special characters URL-encoded.
	 */
	public static String escapeUrlText(String urlText)
	{
		if (urlText == null)
			return null;

		try {
			return URLEncoder.encode(urlText, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * Match the provided <code>text</code> and
	 * <code>blacklistedContents,</code> and replace all matches with
	 * <code>replacement</code>.
	 * 
	 * @param text
	 *        Input text, from which blacklisted contents are to be removed.
	 * @param blacklistedContents
	 *        A regular expression used to find contents to be replaced in
	 *        <code>text</code>.
	 * @param replacement
	 *        Replacement for blacklisted contents in <code>text</code>
	 * @return A string obtained by replacing <code>blacklistedContents</code>
	 *         in <code>text</code> with <code>replacement</code>.
	 */
	private static String removeBlacklistedContents(String text, String blacklistedContents, String replacement)
	{
		if (text == null)
			return null;

		String originalText = text;
		String sanitizedText = originalText.replaceAll(blacklistedContents, replacement);
		return sanitizedText;
	}

	/**
	 * @return Textual representation of stack trace associated with the given
	 *         <code>t</code>, to which sanitization has been applied.
	 */
	public static String sanitizePlainStackTrace(Throwable t)
	{
		String text = getStackTraceText(t);
		return sanitizePlainText(text);
	}

	/**
	 * @return HTML text representation of stack trace associated with the given
	 *         <code>t</code>, in which special HTML characters have been
	 *         replaced with the corresponding entity references, and to which
	 *         sanitization has been applied.
	 */
	public static String sanitizeHtmlStackTrace(Throwable t)
	{
		String text = getStackTraceText(t);
		return sanitizeHtmlText(text, true);
	}

	/**
	 * @param t
	 *        An instance of <code>Throwable</code>, whose stack trace is to be
	 *        returned.
	 * @return A <code>String</code> representation of stack trace associated
	 *         with the given <code>t</code>.
	 */
	private static String getStackTraceText(Throwable t)
	{
		if (t == null)
			return null;

		String text = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(512);
		PrintWriter writer = new PrintWriter(outputStream);

		try {
			t.printStackTrace(writer);
			writer.flush();

			text = outputStream.toString();
			writer.close();
			outputStream.close();
		}
		catch (IOException e) {
		}

		return text;
	}
}
