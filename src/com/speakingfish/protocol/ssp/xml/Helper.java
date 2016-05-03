package com.speakingfish.protocol.ssp.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import javax.annotation.Nullable;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.speakingfish.protocol.ssp.Any;

import static org.w3c.dom.Node.*;

import static com.speakingfish.common.closeable.Closeables.*;

import static com.speakingfish.protocol.ssp.Types.*;
import static com.speakingfish.protocol.ssp.Helper.*;

public class Helper {
    
    public static final DocumentBuilderFactory __docFactory = DocumentBuilderFactory.newInstance();
    //public static final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    public static final TransformerFactory __transformerFactory = TransformerFactory.newInstance();

    public static final String XML_TAG_STRING   = "string"   ;
    public static final String XML_TAG_DECIMAL  = "decimal"  ;
    public static final String XML_TAG_INTEGER  = "int"      ;
    public static final String XML_TAG_FLOAT    = "float"    ;
    public static final String XML_TAG_OBJECT   = "object"   ;
    public static final String XML_TAG_ARRAY    = "array"    ;
    public static final String XML_TAG_BYTEARRAY= "bytearray";

    public static final String XML_ATTR_NAME    = "name"     ;
    
    public static Document ownerDocument(Node src) {
        Document result = src.getOwnerDocument();
        if(null == result) {
            if(DOCUMENT_NODE == src.getNodeType()) {
                result = (Document) src;
            }
        }
        return result;
    }
    
    public static Element appendElement(Node dest, final String tag) {
        final Element result = ownerDocument(dest).createElement(tag);
        dest.appendChild(result);
        return result;
    }

    public static Element appendObject(Node dest, final Any<?> src) {
        final Element result = appendElement(dest, XML_TAG_OBJECT);
        for(int i = 0; i < src.size(); ++i) {
            if(isSerializable(src.item(i))) {
                final Element child = appendAny(result, src.item(i));
                child.setAttribute(XML_ATTR_NAME, src.nameOf(i));
            }
        }
        return result;
    }

    public static Element appendArray(Node dest, final Any<?> src) {
        final Element result = appendElement(dest, XML_TAG_ARRAY);
        for(int i = 0; i < src.size(); ++i) {
            if(isSerializable(src.item(i))) {
                /*Element child =*/ appendAny(result, src.item(i));
            }
        }
        return result;
    }

    public static Element appendValue(Node dest, final String tag, final String value) {
        final Element result = appendElement(dest, tag);
        result.appendChild(ownerDocument(dest).createTextNode(value));
        return result;
    }

    public static final String XML_CDATA_END_1_STRING = "]]";
    public static final String XML_CDATA_END_2_STRING = ">";
    public static final String XML_CDATA_END_STRING   = XML_CDATA_END_1_STRING + XML_CDATA_END_2_STRING;
    
    public static final int    XML_CDATA_END_SPLIT_OFFSET = XML_CDATA_END_1_STRING.length();
    
    public static final byte[] XML_CDATA_END_1_BYTES;
    public static final byte[] XML_CDATA_END_2_BYTES;
    public static final byte[] XML_CDATA_END_BYTES  ;
    
    static {
        byte[] _XML_CDATA_END_1_BYTES = null;
        byte[] _XML_CDATA_END_2_BYTES = null;
        byte[] _XML_CDATA_END_BYTES   = null;
        try {
            _XML_CDATA_END_1_BYTES = XML_CDATA_END_1_STRING.getBytes("UTF8");
            _XML_CDATA_END_2_BYTES = XML_CDATA_END_2_STRING.getBytes("UTF8");
            _XML_CDATA_END_BYTES   = XML_CDATA_END_STRING  .getBytes("UTF8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XML_CDATA_END_1_BYTES = _XML_CDATA_END_1_BYTES;
        XML_CDATA_END_2_BYTES = _XML_CDATA_END_2_BYTES;
        XML_CDATA_END_BYTES   = _XML_CDATA_END_BYTES  ;
    }

    /** origin: https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/base/Preconditions.java
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   *     string using {@link String#valueOf(Object)}
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   */
  public static <T> T checkNotNull(T reference, /*@Nullable*/ Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }
    
   /** origin: https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/primitives/Bytes.java
     * Returns the start position of the first occurrence of the specified {@code
     * target} within {@code array}, or {@code -1} if there is no such occurrence.
     *
     * <p>More formally, returns the lowest index {@code i} such that {@code
     * java.util.Arrays.copyOfRange(array, i, i + target.length)} contains exactly
     * the same elements as {@code target}.
     *
     * @param array the array to search for the sequence {@code target}
     * @param target the array to search for as a sub-sequence of {@code array}
     */
    public static int indexOf(byte[] array, byte[] target, int start) {
        checkNotNull(array, "array");
        checkNotNull(target, "target");
        if (target.length == 0) {
          return 0;
        }

        outer:
        for (int i = start; i < array.length - target.length + 1; i++) {
          for (int j = 0; j < target.length; j++) {
            if (array[i + j] != target[j]) {
              continue outer;
            }
          }
          return i;
        }
        return -1;
      }

    /*
    public static Element appendByteArray(Node dest, final Any<?> src) {
        final Element result = appendElement(dest, XML_TAG_BYTEARRAY);
        final byte[] data = src.asByteArray();
        int start = 0;
        while(true) {
            
            //int next = data.find(XML_CDATA_END, start);
            int next = indexOf(data, XML_CDATA_END_BYTES, start);
            if(next < 0) {
                if(start < data.length) {
                    CDATASection cdata = dest.getOwnerDocument().createCDATASection().
                    TiXmlText* text = new TiXmlText(data.substr(start, data.size() - start));
                    text->SetCDATA(true);
                    result->LinkEndChild(text);
                }
                break;
            }
            next = next + XML_CDATA_END_SPLIT_OFFSET;
            TiXmlText* text = new TiXmlText(data.substr(start, next - start));
            text->SetCDATA(true);
            result->LinkEndChild(text);
            start = next;
        }
        return result;
    }
    */

    public static Element appendAny(Node dest, final Any<?> src) {
        switch(src.type()) {
        case SSP_TYPE_OBJECT     : return appendObject   (dest, src);
        case SSP_TYPE_ARRAY      : return appendArray    (dest, src);
        case SSP_TYPE_STRING     : return appendValue    (dest, XML_TAG_STRING   , src.asString());
        case SSP_TYPE_DECIMAL    : return appendValue    (dest, XML_TAG_DECIMAL  , src.toString());
        case SSP_TYPE_INT_8      : return appendValue    (dest, XML_TAG_INTEGER  , src.toString());
        case SSP_TYPE_INT_16     : return appendValue    (dest, XML_TAG_INTEGER  , src.toString());
        case SSP_TYPE_INT_32     : return appendValue    (dest, XML_TAG_INTEGER  , src.toString());
        case SSP_TYPE_INT_64     : return appendValue    (dest, XML_TAG_INTEGER  , src.toString());
        case SSP_TYPE_FLOAT_32   : return appendValue    (dest, XML_TAG_FLOAT    , src.toString());
        case SSP_TYPE_FLOAT_64   : return appendValue    (dest, XML_TAG_FLOAT    , src.toString());
        case SSP_TYPE_BYTE_ARRAY : return appendValue    (dest, XML_TAG_BYTEARRAY, src.toString()); //appendByteArray(dest, src);
        default: throw new UnsupportedOperationException();
        }
    }

    public static String readTextValue(final Element src) {
        final Node value = src.getFirstChild();
        if(null != value) {
            if(TEXT_NODE == value.getNodeType()) {
                return value.getNodeValue();
            }
        }
        return "";
    }

    public static Any<?> readString   (final Element src) { return any(               readTextValue(src) ); }
    public static Any<?> readDecimal  (final Element src) { return any(new BigDecimal(readTextValue(src))); }
    public static Any<?> readInteger  (final Element src) { return any(Long  .valueOf(readTextValue(src))); }
    public static Any<?> readFloat    (final Element src) { return any(Double.valueOf(readTextValue(src))); }

    public static Any<?> readObject   (final Element src) {
        Any<?> result = anyObject();
        Node node = src.getFirstChild();
        while(null != node) {
            if(ELEMENT_NODE == node.getNodeType()) {
                final Element element = (Element) node;
                Any<?> child = readAny(element);
                if(null != child) {
                    result.add(element.getAttribute(XML_ATTR_NAME), child);
                }
            }
            node = node.getNextSibling();
        }
        return result;
    }

    public static Any<?> readArray    (final Element src) {
        Any<?> result = anyArray();
        Node node = src.getFirstChild();
        while(node != null) {
            if(ELEMENT_NODE == node.getNodeType()) {
                final Element element = (Element) node;
                Any<?> child = readAny(element);
                if(null != child) {
                    result.add(child);
                }
            }
            node = node.getNextSibling();
        }
        return result;
    }

    /* TODO
    public static Any<?> readByteArray(final Element src) {
        Stringstream data;
        Node final * node = src.FirstChild();
        while(node) {
            final TiXmlText* textNode = node->ToText();
            if(textNode  textNode->CDATA()) {
                data << textNode->ValueStr();
            }
            node = node->NextSibling();
        }
        return newAnyByteArray(data.str());
    }
    */


    public static Any<?> readAny(final Element src) {
        if(null != src) {
            String tag = src.getTagName();
            if     (XML_TAG_STRING .equals(tag)) return readString   (src);
            else if(XML_TAG_DECIMAL.equals(tag)) return readDecimal  (src);
            else if(XML_TAG_INTEGER.equals(tag)) return readInteger  (src);
            else if(XML_TAG_FLOAT  .equals(tag)) return readFloat    (src);
            else if(XML_TAG_OBJECT .equals(tag)) return readObject   (src);
            else if(XML_TAG_ARRAY  .equals(tag)) return readArray    (src);
          //else if(XML_TAG_BYTEARRAY== tag) return readByteArray(src);
            else throw new UnsupportedOperationException();
        }
        return null;
    }

    public static String anyToXmlString(final Any<?> src) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = __docFactory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        final Document document = docBuilder.newDocument();
        appendAny(document, src);
        Transformer transformer;
        try {
            transformer = __transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch(TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        final DOMSource source = new DOMSource(document);
        final StringWriter temp = new StringWriter();
        final StreamResult result = new StreamResult(temp);
        try {
            transformer.transform(source, result);
        } catch(TransformerException e) {
            throw new RuntimeException(e);
        } 
        return temp.toString();
    }

    public static boolean anyToXmlFile(final Any<?> src, FileWriter dest) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = __docFactory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        final Document document = docBuilder.newDocument();
        appendAny(document, src);
        Transformer transformer;
        try {
            transformer = __transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        } catch(TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        final DOMSource source = new DOMSource(document);
        try {
            try {
                final StreamResult result = new StreamResult(dest);
                transformer.transform(source, result);
            } finally {
                catchClose(dest);
            }
        } catch(TransformerException e) {
            return false;
        } 
        return true;
    }
    
    public static boolean anyToXmlFile(final Any<?> src, final String destFilename) {
        try {
            return anyToXmlFile(src, new FileWriter(destFilename));
        } catch(IOException e) {
            return false;
        } 
    }

    public static boolean anyToXmlFile(final Any<?> src, final File destFile) {
        try {
            return anyToXmlFile(src, new FileWriter(destFile));
        } catch(IOException e) {
            return false;
        } 
    }

    
    public static Any<?> xmlStringToAny(final String src) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = __docFactory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            final ByteArrayInputStream temp = new ByteArrayInputStream(src.getBytes("UTF8"));
            final Document document = docBuilder.parse(temp);
            return readAny(document.getDocumentElement());
        } catch(SAXException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Any<?> xmlFileToAny(final String srcFilename) {
        return xmlFileToAny(new File(srcFilename));
    }
    
    public static Any<?> xmlFileToAny(final File srcFile) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = __docFactory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            final Document document = docBuilder.parse(srcFile);
            return readAny(document.getDocumentElement());
        } catch(SAXException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
