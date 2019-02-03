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
import de.mossgrabers.framework.daw.data.ChannelData;


/**
 * The device left command.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class DeviceLeftCommand extends AbstractTriggerCommand<APCControlSurface, APCConfiguration>
{
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public DeviceLeftCommand (final Model model, final APCControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void executeNormal (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        final CursorDeviceProxy cd = this.model.getCursorDevice ();
        final ChannelData layer = cd.getSelectedLayerOrDrumPad ();
        if (!cd.hasLayers () || layer == null)
            cd.selectPrevious ();
        else
            cd.selectLayer (layer.getIndex () == 0 ? 0 : layer.getIndex () - 1);
    }


    /** {@inheritDoc} */
    @Override
    public void executeShifted (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        // Exit layer
        final CursorDeviceProxy cd = this.model.getCursorDevice ();
        final ChannelData layer = cd.getSelectedLayerOrDrumPad ();
        if (!cd.hasLayers () || layer == null)
        {
            if (cd.isNested ())
            {
                cd.selectParent ();
                cd.selectChannel ();
            }
        }
        else
            layer.setSelected (false);
    }
}
