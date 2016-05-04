package com.Duckelekuuk.PPF.GameFrame.Core;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Duco on 23-2-2016.
 */

@Getter
@Setter
public class PreventionSet {

    private boolean AllowedToBuild;
    private boolean AllowedToChat;
    private boolean AllowedToDestroy;
    private boolean AllowedToPVP;
    private boolean AllowedToPVE;
    private boolean AllowedToInteract;

    public PreventionSet() {
    }
}
