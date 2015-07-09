package fr.solucom.communitylibrary;

/**
 * Created by wasier on 30/06/2015.
 */

/**
 * This java class represents general data hosted on the server side.
 * General data represents all shared data by the floors. (e.g General information concerning Solucom, KM, restaurant menus)
 * General data possesses:
 * two Knowledge Management Articles,
 * two restaurant menus (one for each restaurant)
 * two general Solucom articles
 *
 * @see KMArticle
 * @see SetMenu
 * @see SolucomArticle
 */
public class GeneralData {

    private String _id;
    //The title of the Knowledge management page
    private String kmtitle;
    //The title of the restaurant menu page
    private String setmenutitle;
    //The title of general information concerning solucom page
    private String solucomtitle;

    //The first KM article
    private KMArticle km1;
    //The second KM article
    private KMArticle km2;
    //The first restaurant menu (i.e. The "Charpentier" menu)
    private SetMenu menu1;
    //The second restaurant menu (i.e. The "Musée" menu)
    private SetMenu menu2;
    //The first solucom article
    private SolucomArticle solucom1;
    //The second solucom article
    private SolucomArticle solucom2;

    public String getKMTitle() {
        return kmtitle;
    }

    public String getSetMenuTitle() {
        return setmenutitle;
    }

    public String getSolucomTitle() {
        return solucomtitle;
    }

    public KMArticle getKmArticle1() {
        return km1;
    }

    public KMArticle getKmArticle2() {

        return km2;
    }

    public SetMenu getSetMenu1() {
        return menu1;
    }

    public SetMenu getSetMenu2() {
        return menu2;
    }

    public SolucomArticle getSolucomArticle1() {
        return solucom1;
    }

    public SolucomArticle getSolucomArticle2() {
        return solucom2;
    }
}