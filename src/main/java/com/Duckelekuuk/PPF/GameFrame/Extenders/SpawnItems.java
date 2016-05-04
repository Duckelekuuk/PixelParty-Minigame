package com.Duckelekuuk.PPF.GameFrame.Extenders;

import javafx.scene.paint.Material;

import java.util.HashMap;


/**
 * @AUTHOR Duco.
 * Description
 */
public abstract class SpawnItems {

    public abstract Material getHelmet();
    public abstract Material getChestplate();
    public abstract Material getLeggings();
    public abstract Material getBoots();

    public abstract HashMap<Material, Integer> getItems();
}
