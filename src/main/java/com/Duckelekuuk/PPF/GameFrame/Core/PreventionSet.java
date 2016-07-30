package com.Duckelekuuk.PPF.GameFrame.Core;

import lombok.Getter;
import lombok.Setter;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@Setter
public class PreventionSet {

    private boolean AllowedToPlaceBlocks = false;
    private boolean AllowedToChat = true;
    private boolean AllowedToDestroyBlocks = false;
    private boolean AllowedToDamageBlocks = false;
    private boolean AllowedToPVP = false;
    private boolean AllowedToPVE = false;
    private boolean AllowedToInteract = false;
    private boolean AllowedToChangeFoodLevel = false;

    public PreventionSet() {
    }
}
