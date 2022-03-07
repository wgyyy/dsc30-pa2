import org.junit.Test;

import static org.junit.Assert.*;

public class TextEditorTest {

    @Test
    public void getText() {
        TextEditor test = new TextEditor();
        assertEquals("", test.getText());
        test.insert(0, "hello");
        assertEquals("hello", test.getText());
        test.insert(5, " world");
        assertEquals("hello world", test.getText());
        test.insert(5, " yes");
        assertEquals("hello yes world", test.getText());
    }

    @Test
    public void length() {
        TextEditor test = new TextEditor();
        assertEquals(0, test.length());
        test.insert(0, "hello");
        assertEquals(5, test.length());
        test.insert(5, " world");
        assertEquals(11, test.length());
        test.delete(5, 11);
        System.out.println(test.getText());
        assertEquals(5, test.length());
    }

    @Test
    public void caseConvert() {
        TextEditor test = new TextEditor();
        test.insert(0,"HeL !lo");
        test.caseConvert(0,7);
        assertEquals("hEl !LO", test.getText());
    }

    @Test
    public void insert() {
        TextEditor test = new TextEditor();
        test.insert(0,"hello");
        assertEquals("hello", test.getText());
    }

    @Test
    public void delete() {
        TextEditor test = new TextEditor();
        test.insert(0,"hel  lo");
        test.delete(3,5);
        assertEquals("hello", test.getText());
    }

    @Test
    public void undo() {
        TextEditor test = new TextEditor();
        test.insert(0,"hel  lo");
        test.delete(3,5);
        test.undo();
        assertEquals("hel  lo", test.getText());
        test.insert(0,"ha");
        test.undo();
        assertEquals("hel  lo", test.getText());
        test.caseConvert(0,2);
        test.undo();
        assertEquals("hel  lo", test.getText());
    }

    @Test
    public void redo() {
        TextEditor test = new TextEditor();
        test.insert(0,"hel  lo");
        test.delete(3,5);
        test.undo();
        test.redo();
        assertEquals("hello", test.getText());
        test.insert(0,"ha");
        test.undo();
        test.redo();
        assertEquals("hahello", test.getText());
        test.caseConvert(0,2);
        test.undo();
        assertEquals("hahello", test.getText());
    }
}