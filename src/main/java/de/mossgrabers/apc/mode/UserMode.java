// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.apc.mode;

import de.mossgrabers.apc.controller.APCControlSurface;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.daw.data.TrackData;


/**
 * Remote control knob mode.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class UserMode extends BaseMode
{
    /**
     * Constructor.
     *
     * @param surface The control surface
     * @param model The model
     */
    public UserMode (final APCControlSurface surface, final Model model)
    {
        super (surface, model, 3, 64);
    }


    /** {@inheritDoc} */
    @Override
    public void setValue (final int index, final int value)
    {
        this.model.getCurrentTrackBank ().setPan (index, value);
    }


    /** {@inheritDoc} */
    @Override
    public Integer getValue (final int index)
    {
        final TrackData track = this.model.getCurrentTrackBank ().getTrack (index);
        return track.doesExist () ? Integer.valueOf (track.getPan ()) : null;
    }
}
