/*
    Name: Gaoying Wang
    PID:  A16131629
 */

import java.nio.charset.CharacterCodingException;
import java.util.Locale;

/**
 * This class help users edit the text.
 * @author Gaoying Wang
 * @since  ${2022-03-04}
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
        if (i > this.text.length() || j > this.text.length() || i < 0 || j < 0 || i >= j) {
            throw  new IllegalArgumentException();
        }
        for ( int x = i; x < j; x++) {
            char convert_to_char = text.charAt(x);
            if (Character.isLetter(convert_to_char)) {
                String return_String;
                if (Character.isUpperCase(convert_to_char)) {
                    return_String = String.valueOf(convert_to_char);
                    return_String = return_String.toLowerCase();
                } else {
                    return_String = String.valueOf(convert_to_char);
                    return_String = return_String.toUpperCase();
                }
                this.text= this.text.substring(0,x) + return_String + this.text.substring(x+1);
            }
        }
        undo.push(i);
        undo.push(j);
        undo.push(0);
    }

    public void insert(int i, String input) {
        if (input == null ) {
            throw  new NullPointerException();
        }
        if (i > this.text.length()) {
            throw new IllegalArgumentException();
        }
        if (this.length() == 0) {
            this.text = input;
        } else {
            String first_piece = this.text.substring(0,i);
            String second_piece = this.text.substring(i);
            this.text = first_piece + input + second_piece;
        }
        this.undo.push(i);
        this.undo.push(i+input.length());
        this.undo.push(1);
    }

    public void delete(int i, int j) {
        if( i > this.text.length() || i < 0 ||j > this.text.length() || i >= j || j < 0) {
            throw new IllegalArgumentException();
        }
        String deleted_text= this.text.substring(i,j);
        String first_piece = this.text.substring(0,i);
        String second_piece = this.text.substring(j);
        this.deletedText.push(deleted_text);
        this.text = first_piece + second_piece;
        this.undo.push(i);
        this.undo.push(2);
    }

    public boolean undo() {
        int start=0;
        int end=0;
        if (undo.isEmpty()){
            return false;
        }else{
            if (undo.peek()==0){
                undo.pop();
                end=undo.pop();
                start=undo.pop();
                this.caseConvert(start,end);
                redo.push(start);
                redo.push(end);
                redo.push(0);
                return true;
            }else if(undo.peek()==1){
                undo.pop();
                end=undo.pop();
                start=undo.pop();
                this.delete(start,end);
                redo.push(start);
                redo.push(1);
                return true;
            }else{
                undo.pop();
                start=undo.pop();
                String inserted=deletedText.pop();
                this.insert(start,inserted);
                redo.push(start);
                redo.push(start+inserted.length());
                redo.push(2);
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
            if (redo.peek()==0){
                redo.pop();
                end=redo.pop();
                start=redo.pop();
                this.caseConvert(start,end);
                return true;
            }else if(redo.peek()==1){
                redo.pop();
                start=redo.pop();
                this.insert(start,deletedText.pop());
                return true;
            }else{
                redo.pop();
                end=redo.pop();
                start=redo.pop();
                this.delete(start,end);
                return true;
            }
        }
    }
}