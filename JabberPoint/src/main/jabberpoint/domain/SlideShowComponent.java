package main.jabberpoint.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SlideShowComponent implements Iterable
{
    private Map<String , String > styles = new HashMap<>();

    public abstract List<? extends Content> getContent();

    public Map<String, String> getMetadata(){
        return Metadata.getInstance().metadata;
    }

<<<<<<< Updated upstream
    public void addStyles(Map<String, String> styles){
        this.styles.putAll(styles);
    }

    public Map<String, String> getStyles() {
        return styles;
    }
=======
//    Map<String, String> style = new HashMap<>();
//    {
//        style.put("font", "Arial");
//        style.put("size", "36");
//
//    }

//    {
//        Label .setFont(new Font(style.get("font") == null ? DEFAULT : style.get("font")));
//    }
>>>>>>> Stashed changes
}