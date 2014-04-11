package core.pages;

import core.base.PageBase;

public class HelpPage extends PageBase {
    private static final String TITLE = "Документация ViPNet";

    public static void shouldAppear(){
        shouldAppear(TITLE);
    }
}
