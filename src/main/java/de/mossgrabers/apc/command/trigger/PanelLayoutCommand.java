// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.apc.command.trigger;

import de.mossgrabers.apc.APCConfiguration;
import de.mossgrabers.apc.controller.APCControlSurface;
import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.ApplicationProxy;


/**
 * Command handle the panel layout button.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class PanelLayoutCommand extends AbstractTriggerCommand<APCControlSurface, APCConfiguration>
{
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public PanelLayoutCommand (final Model model, final APCControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void executeNormal (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        final ApplicationProxy app = this.model.getApplication ();
        switch (app.getPanelLayout ())
        {
            case "ARRANGE":
                app.setPanelLayout ("MIX");
                break;
            case "MIX":
                app.setPanelLayout ("EDIT");
                break;
            // "EDIT":
            default:
                app.setPanelLayout ("ARRANGE");
                break;
        }
    }


    /** {@inheritDoc} */
    @Override
    public void executeShifted (final ButtonEvent event)
    {
        if (event == ButtonEvent.DOWN)
            this.model.getCursorDevice ().toggleWindowOpen ();
    }
}
