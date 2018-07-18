// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.apc.command.trigger;

import de.mossgrabers.apc.APCConfiguration;
import de.mossgrabers.apc.controller.APCControlSurface;
import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.CursorDeviceProxy;


/**
 * The browser command.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class BankLeftCommand extends AbstractTriggerCommand<APCControlSurface, APCConfiguration>
{
    static final int PARAMETER = 6;
    
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public BankLeftCommand (final Model model, final APCControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void executeNormal (final ButtonEvent event)
    {
        /* ORIGINAL FUNCTION
         * if (event == ButtonEvent.DOWN)
         *    this.model.getCursorDevice ().previousParameterPage ();
         */
         
        if (event == ButtonEvent.DOWN)
        {
            final CursorDeviceProxy cd = this.model.getCursorDevice ();
            if (!cd.doesExist ())
                return;
            
            if (cd.getParameterValue(PARAMETER) == 0)
            {
                cd.setParameterMax(PARAMETER);
            }
            else
            {
                cd.setParameter(PARAMETER, 0);
            }
        }
    }


    /** {@inheritDoc} */
    @Override
    public void executeShifted (final ButtonEvent event)
    {
        if (event == ButtonEvent.DOWN)
            this.model.getCursorDevice ().previousParameterPage ();
    }
}
