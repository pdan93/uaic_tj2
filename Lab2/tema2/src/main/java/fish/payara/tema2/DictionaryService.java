package fish.payara.tema2;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author x
 */
public class DictionaryService {
    private ArrayList<DictionaryBean> dictionaries = new ArrayList<>();
    
    public DictionaryBean getDictionary(String language) {
        for (int i=0; i<dictionaries.size(); i++) {
            if (dictionaries.get(i).getLanguage().equals(language)) {
                return dictionaries.get(i);
            }
        }
        
        return null;
    }
    
    public DictionaryBean insertEntry(String language, String word, String definition) {
        DictionaryBean dict = getDictionary(language);
        if (dict == null) {
            System.out.println("new dict");
            dict = new DictionaryBean();
            dict.setLanguage(language);
        }
        ArrayList<String> words = dict.getWords();
        ArrayList<String> definitions = dict.getDefinitions();
        
        words.add(word);
        definitions.add(definition);
        
        dict.setWords(words);
        dict.setDefinitions(definitions);
        
        dictionaries.add(dict);
        
        return dict;
    }
    
    public Boolean verifyEntry(String language, String word) {
        DictionaryBean dict = getDictionary(language);
        if (dict != null) {
            if (dict.hasWord(word))
                return false;
        }
        return true;
    } 
    
    public Boolean verifyLanguage(String language) {
        DictionaryBean dict = getDictionary(language);
        if (dict != null) {
            return true;
        }
        return false;
    }
    
    public Boolean verifyParameters(String language, String word, String definition) {
        if (language.length() < 3) {
            return false;
        }
        if (word.length() < 3) {
            return false;
        }
        if (definition.length() < 3) {
            return false;
        }
        return true;
    }
}
