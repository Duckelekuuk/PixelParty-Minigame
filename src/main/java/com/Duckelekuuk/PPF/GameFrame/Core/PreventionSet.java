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

    private boolean AllowedToPlaceBlocks;
    private boolean AllowedToChat;
    private boolean AllowedToDestroyBlocks;
    private boolean AllowedToDamageBlocks;
    private boolean AllowedToPVP;
    private boolean AllowedToPVE;
    private boolean AllowedToInteract;

    public PreventionSet() {
    }
}
