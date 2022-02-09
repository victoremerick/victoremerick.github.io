package app7food;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class Main extends JFrame {

    StyleSheet styleSheet = new StyleSheet();
    HTMLDocument htmlDocument;
    HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
    Element bodyElement;

    public static void main(String[] args) throws Exception {
        Main jTextPaneApp = new Main();
        jTextPaneApp.setVisible(true);
        Thread.currentThread().sleep(1000);
        jTextPaneApp.change();
    }

    public Main() {
        setSize(400, 400);
        styleSheet.addRule(".someclass1 {color: blue;}");
        styleSheet.addRule(".someclass2 {color: green;}");

        htmlEditorKit.setStyleSheet(styleSheet);
        htmlDocument = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        JTextPane jTextPane = new JTextPane();
        jTextPane.setEditorKit(htmlEditorKit);
        jTextPane.setDocument(htmlDocument);

        try {
            Element htmlElement = htmlDocument.getRootElements()[0];
            bodyElement = htmlElement.getElement(0);

            Container contentPane = getContentPane();
            contentPane.add(jTextPane, BorderLayout.CENTER);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            addContent("<span class=someclass1>test 1</span><br>");
            addContent("<span class=someclass2>test 2</span><br>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reapplyStyles() {
        Element sectionElem = bodyElement
                .getElement(bodyElement.getElementCount() - 1);
        int paraCount = sectionElem.getElementCount();
        for (int i = 0; i < paraCount; i++) {
            Element e = sectionElem.getElement(i);
            int rangeStart = e.getStartOffset();
            int rangeEnd = e.getEndOffset();
            htmlDocument.setParagraphAttributes(rangeStart, rangeEnd - rangeStart,
                    e.getAttributes(), true);
        }
    }

    public void change() throws Exception {
        styleSheet = htmlEditorKit.getStyleSheet();
        styleSheet.addRule(".someclass1 {color: red;}");
        reapplyStyles();
        addContent("<span class=someclass1>test 3</span><br>");
    }

    private void addContent(String content) throws Exception {
        Element contentElement = bodyElement.getElement(bodyElement
                .getElementCount() - 1);

        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<span class=someclass>" + content + "</span><br>");

        htmlDocument.insertBeforeEnd(contentElement, sbHtml.toString());
    }
}
