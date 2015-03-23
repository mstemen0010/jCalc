/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * FXML Controller class
 *
 * @author matthew.g.stemen
 */
public class JCalcFXMLController implements Initializable {

    @FXML
    TextField displayScreen;

    StringBuilder screenValue;

    ArrayList<KeyType> keyEntry;

    String currentEval;

    public enum KeyType {

        Undefined(""),
        Key_1("1"),
        Key_2("2"),
        Key_3("3"),
        Key_4("4"),
        Key_5("5"),
        Key_6("6"),
        Key_7("7"),
        Key_8("8"),
        Key_9("9"),
        Key_0("0"),
        Key_Point("."),
        Key_Div(" / "),
        Key_Mult(" * "),
        Key_Plus(" + "),
        Key_Sub(" - "),
        Key_Power(" ^ "),
        Key_OpenPeren(" ("),
        Key_ClosePeren(") "),
        Key_Clear("AC"),
        Key_ClearEntry("CE"),
        ;

        private String text;

        KeyType(String value) {
            text = value;
        }

        /**
         * @param text
         */

        /**
         *
         * @return
         */
        public KeyType getKeyType() {
            KeyType keyType = KeyType.Undefined;

            return this;
        }

        static public KeyType getKeyType(String keyTypeValue) {
            KeyType keyTypeToReturn = KeyType.Undefined;

            switch (keyTypeValue) {
                case "1":
                    keyTypeToReturn = KeyType.Key_1;
                    break;
                case "2":
                    keyTypeToReturn = KeyType.Key_2;
                    break;
                case "3":
                    keyTypeToReturn = KeyType.Key_3;
                    break;
                case "4":
                    keyTypeToReturn = KeyType.Key_4;
                    break;
                case "5":
                    keyTypeToReturn = KeyType.Key_5;
                    break;
                case "6":
                    keyTypeToReturn = KeyType.Key_6;
                    break;
                case "7":
                    keyTypeToReturn = KeyType.Key_7;
                    break;
                case "8":
                    keyTypeToReturn = KeyType.Key_8;
                    break;
                case "9":
                    keyTypeToReturn = KeyType.Key_9;
                    break;
                case "0":
                    keyTypeToReturn = KeyType.Key_0;
                    break;
                case "/":
                    keyTypeToReturn = KeyType.Key_Div;
                    break;
                case "*":
                    keyTypeToReturn = KeyType.Key_Mult;
                    break;
                case "+":
                    keyTypeToReturn = KeyType.Key_Plus;
                    break;
                case "-":
                    keyTypeToReturn = KeyType.Key_Sub;
                    break;
                case "^":
                    keyTypeToReturn = KeyType.Key_Power;
                    break;
                case "(":
                    keyTypeToReturn = KeyType.Key_OpenPeren;
                    break;
                case ")":
                    keyTypeToReturn = KeyType.Key_ClosePeren;
                    break;
                case "AC":
                    keyTypeToReturn = KeyType.Key_Clear;
                    break;
                case "CE":
                    keyTypeToReturn = KeyType.Key_ClearEntry;
                    break;
                case ".":
                    keyTypeToReturn = KeyType.Key_Point;
                    break;
                default:
                    break;

            }
            return keyTypeToReturn;

        }

        public String getKeyValue() {
            return this.text;
        }

    }

    @FXML
    public void grabKey(KeyEvent event) {
        System.out.println("Got Key Event: " + event.toString());
        String t = event.getCharacter();
        event.consume();
        System.out.println("Got Key Event: " + event.toString());

        
         System.out.println("Value is: " + t );
         this.displayScreen.setText(t);
         
         KeyType kt =  KeyType.getKeyType(t);
         this.displayScreen.setText(kt.getKeyValue());
        if( t != null )
        {                        
            switch( kt )
            {
                case Key_1:
                case Key_2:                    
                case Key_3:                                    
                case Key_4:                                    
                case Key_5:                                                        
                case Key_6:
                case Key_7:
                case Key_8:
                case Key_9:
                case Key_0:
                    t = kt.getKeyValue();
                    break;
                case Key_Mult:
                    t = "  *  ";
                    break;
                case Key_Sub: 
                    t = " - ";
                    break;
                case Key_Div:
                    t = " / ";
                    break;
                case Key_Plus:
                    t = " + ";
                    break;
                case Key_Power:
                    t = " ^ ";
                    break;
                case Key_OpenPeren:
                    t = " ( ";
                    break;
                case Key_ClosePeren:
                    t = ")";
                    break;
                case Key_Clear:
                    this.clearScreen();
                    break;
                    
            }
        
        }
        keyEntry.add(kt);
        if ("\r".equals(t)) {
            this.evalScreen();
        } else {
            this.displayToScreen(t);
        }
    }

    @FXML
    public void showKey(ActionEvent event) {
        System.out.println("Got Event");
        String t = null;
        if (Button.class.isInstance(event.getSource())) {
            Button currentButton = (Button) event.getSource();
            t = currentButton.getText();
        }

        Button currentButton = (Button) event.getSource();
        String value = event.getSource().toString();
        System.out.println("Value is: " + value);
        // this.displayScreen.setText(value);

        // this.displayScreen.setText(currentButton.getText());
        if (currentButton != null) {
            KeyType kt = KeyType.getKeyType(t);

            switch (kt) {
                case Key_1:
                case Key_2:
                case Key_3:
                case Key_4:
                case Key_5:
                case Key_6:
                case Key_7:
                case Key_8:
                case Key_9:
                case Key_0:
                case Key_Mult:
                case Key_Sub:
                case Key_Div:
                case Key_Plus:
                case Key_Power:
                case Key_OpenPeren:
                case Key_ClosePeren:
                case Key_Point:
                    t = kt.getKeyValue();
                    break;
                case Key_Clear:
                    this.clearScreen();
                    break;

            }
            keyEntry.add(kt);
            System.out.println("Key Op is: " + "\"" + kt.getKeyValue() + "\"");
            this.displayToScreen("");
        }
    }

    private void displayToScreen(String value) {
        // add current value to string builder
        // this.screenValue.append(value);
        // this.displayScreen.setText(this.screenValue.toString());
        if (this.currentEval != null) {
            this.screenValue = new StringBuilder( this.currentEval );
        } 
        else {
            if (value != null) {
                this.screenValue = new StringBuilder(value);
            } else {
                this.screenValue = new StringBuilder();
            }
            // iterate through the key strokes to build the screen
        }
   
            Iterator<KeyType> it = this.keyEntry.iterator();
            while (it.hasNext()) {
                this.screenValue.append(it.next().getKeyValue());
            }
            this.displayScreen.setText(screenValue.toString());
        

    }

    @FXML
    public void clearEntry() {
        // get the current string display value and current "model" value
//        String currScreenValue = this.screenValue.toString();
//        String currDisplayScreenValue = this.displayScreen.getText().trim();
//
//        // "chew" off the value from the current end char to the next whitespace
//        int lastSpace = currDisplayScreenValue.lastIndexOf(' ');
//        System.out.println("Last index of space is; " + lastSpace);
//        String newScreenValue = currDisplayScreenValue.substring(lastSpace, currDisplayScreenValue.length());
//
//        System.out.println("new display is: " + newScreenValue);
       int  numKeyOps = this.keyEntry.size();
       try
       {
           keyEntry.remove( numKeyOps - 1 );
           this.displayToScreen("");
       }
       catch( ArrayIndexOutOfBoundsException e )
       {
        
           this.displayToScreen("0.0");
       }
       
       
    }

    @FXML
    public void clearScreen(ActionEvent event) {
        clearScreen();
    }

    @FXML
    public void evalScreen() {

        String screenValue = this.displayScreen.getText();
        System.out.println("Screen To Eval is: " + screenValue);
        if (screenValue.contains("^") || screenValue.contains("(") || screenValue.contains(")")) {
            double val = this.complexEval(screenValue);
            screenValue = String.valueOf(val);
            // this.clearScreen();
            // this.displayScreen.setText(screenValue);

        } else {
               // screenValue = this.simpleEval(screenValue);
      
            double val = this.complexEval(screenValue);        	
            screenValue = String.valueOf(val);
        }
        // 
        this.currentEval = screenValue;
        this.displayToScreen(screenValue);
    }

    public void clearScreen() {
        this.screenValue = new StringBuilder("");
        this.displayScreen.setText("");
        this.keyEntry.clear();
        this.currentEval = "";
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.screenValue = new StringBuilder();
        this.keyEntry = new ArrayList<>();
        this.currentEval = null;
    }

    public String simpleEval(String str) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        str = this.displayScreen.getText();
        String value = null;
        try {
            System.out.println(engine.eval(str));
            value = engine.eval(str).toString();
            this.clearScreen();
            // this.displayScreen.setText(value);
        } catch (ScriptException ex) {
            Logger.getLogger(JCalcFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public double complexEval(String str) {
        this.clearScreen();
        class Parser {

            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) {
                    eatChar();
                }
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) {
                    throw new RuntimeException("Unexpected: " + (char) c);
                }
                return v;
            }

        // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor | term brackets
            // factor = brackets | number | factor `^` factor
            // brackets = `(` expression `)`
            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') {
                            eatChar();
                        }
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '(') { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') {
                        eatChar();
                    }
                } else { // numbers
                    if (c == '+' || c == '-') { // unary plus & minus
                        negate = c == '-';
                        eatChar();
                        eatSpace();
                    }
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char) c);
                        eatChar();
                    }
                    if (sb.length() == 0) {
                        throw new RuntimeException("Unexpected: " + (char) c);
                    }
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) {
                    v = -v; // exponentiation has higher priority than unary minus: -3^2=-9
                }
                return v;
            }
        }
        return new Parser().parse();
    }

}
