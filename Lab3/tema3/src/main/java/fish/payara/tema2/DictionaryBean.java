package fish.payara.tema2;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author x
 */
public class DictionaryBean implements Serializable {

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }
    
    public Boolean hasWord(String word) {
        for (int i=0; i<this.words.size(); i++) {
            if (this.words.get(i).equals(word)) {
                return true;
            }
        }
        return false;
    }
    
    public Map<String,String> getEntries() {
        Map<String, String> dict = new HashMap<String, String>();
        for (int i=0; i<this.words.size(); i++) {
            dict.put(words.get(i), definitions.get(i));
        }
        return dict;
    }
    
    
    public String getWordDefinition(String word) {
        for (int i=0; i<this.words.size(); i++) {
            if (this.words.get(i).equals(word)) {
                return this.definitions.get(i);
            }
        }
        return null;
    }
    
    
    private String language="null";
    private ArrayList<String>words=new ArrayList<String>();
    private ArrayList<String>definitions=new ArrayList<String>();
}
