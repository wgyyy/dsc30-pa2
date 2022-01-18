/*
    Name: Gaoying Wang
    PID:  A16131629
 */

/**
 * This class help users edit the text.
 * @author Gaoying Wang
 * @since  ${2022-01-16}
 */
public class TextEditor {

    /* instance variables */
    private String text;
    private IntStack undo;
    private StringStack deletedText;
    private IntStack redo;
    private StringStack insertedText;

    public TextEditor() {
        text="";
        undo=new IntStack(5);
        deletedText=new StringStack(5);
        redo=new IntStack(5);
        insertedText=new StringStack(5);
    }

    public String getText() {
        return text;
    }

    public int length() {
        return text.length();
    }

    public void caseConvert(int i, int j) {
        StringBuffer newStr=new StringBuffer(1);
        for (int x=i;x<j;x++){
            if (Character.isLetter(text.charAt(x))){
                if (Character.isUpperCase(text.charAt(x))){
                    newStr.setCharAt(x,Character.toLowerCase(text.charAt(x)));
                }else if (Character.isLowerCase(text.charAt(x))){
                    newStr.setCharAt(x,Character.toUpperCase(text.charAt(x)));
                }
            }
        }
        undo.push(i);
        undo.push(j);
        undo.push(0);
    }

    public void insert(int i, String input) {
        String new_text=new String();
        insertedText.push(input);
        for (int x=0;x<text.length();x++){
            new_text+=text.charAt(x);
            if (x==i){
                new_text+=input;
            }
        }
        text=new_text;
        undo.push(i);
        undo.push(i+input.length());
        undo.push(1);
    }

    public void delete(int i, int j) {
        String deleted_text=new String();
        for (int y=i;y<j;y++){
            deleted_text+=text.charAt(y);
        }
        deletedText.push(deleted_text);
        String new_text=new String();
        for (int x=0;x<text.length();x++){
            if (x==i){
                x=j;
                new_text+=text.charAt(x);
            }else {
                new_text += text.charAt(x);
            }
        }
        text=new_text;
        undo.push(i);
        undo.push(j);
        undo.push(2);
    }

    public boolean undo() {
        int start=0;
        int end=0;
        if (undo.isEmpty()){
            return false;
        }else{
            if (undo.pop()==0){
                end=undo.pop();
                start=undo.pop();
                caseConvert(start,end);
                redo.push(start);
                redo.push(end);
                redo.push(0);
                return true;
            }else if(undo.pop()==1){
                end=undo.pop();
                start=undo.pop();
                delete(start,end);
                redo.push(start);
                redo.push(end);
                redo.push(1);
                return true;
            }else{
                end=undo.pop();
                start=undo.pop();
                String inserted=deletedText.pop();
                insert(start,inserted);
                redo.push(start);
                redo.push(start+inserted.length());
                return true;
            }
        }
    }

    public boolean redo() {
        if (redo.isEmpty()){
            return false;
        }else{
            int start=0;
            int end=0;
            if (redo.pop()==0){
                end=redo.pop();
                start=redo.pop();
                caseConvert(start,end);
                return true;
            }else if(redo.pop()==1){
                end=redo.pop();
                start=redo.pop();
                insert(start,deletedText.pop());
                return true;
            }else{
                end=redo.pop();
                start=redo.pop();
                delete(start,end);
                return true;
            }
        }
    }
}