// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.apc.command.trigger;

import de.mossgrabers.apc.APCConfiguration;
import de.mossgrabers.apc.controller.APCControlSurface;
import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;


/**
 * Command handle the nudge buttons.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class NudgeCommand extends AbstractTriggerCommand<APCControlSurface, APCConfiguration>
{
    private boolean isPlus;
    private boolean isTempoChange;


    /**
     * Constructor.
     *
     * @param isPlus True if nudge positive
     * @param model The model
     * @param surface The surface
     */
    public NudgeCommand (final boolean isPlus, final Model model, final APCControlSurface surface)
    {
        super (model, surface);
        this.isPlus = isPlus;
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        if (event == ButtonEvent.DOWN)
            this.isTempoChange = true;
        else if (event == ButtonEvent.UP)
            this.isTempoChange = false;
        this.doChangeTempo ();
    }


    private void doChangeTempo ()
    {
        if (!this.isTempoChange)
            return;
        this.model.getTransport ().changeTempo (this.isPlus);
        this.surface.scheduleTask (this::doChangeTempo, 200);
    }
}
