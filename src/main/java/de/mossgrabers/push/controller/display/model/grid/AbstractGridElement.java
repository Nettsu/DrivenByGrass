package de.mossgrabers.push.controller.display.model.grid;

import de.mossgrabers.push.controller.display.model.LayoutSettings;

import com.bitwig.extension.api.GraphicsOutput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;


/**
 * Abstract base class for an element in the grid.
 *
 * Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public abstract class AbstractGridElement implements GridElement
{
    /** The maximum possible value for a parameter. */
    private static double   maxValue = 1024;

    private final String    name;
    private final String    icon;
    private final Color     color;
    private final boolean   isSelected;

    protected final boolean isMenuSelected;
    protected final String  menuName;


    /**
     * Constructor.
     *
     * @param menuName The text for the menu
     * @param isMenuSelected True if the menu is selected
     * @param icon The icon to use in the header, may be null
     * @param name The of the grid element (track name, parameter name, etc.)
     * @param color The color to use for the header, may be null
     * @param isSelected True if the grid element is selected
     */
    public AbstractGridElement (final String menuName, final boolean isMenuSelected, final String icon, final String name, final Color color, final boolean isSelected)
    {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.isSelected = isSelected;
        this.menuName = menuName;
        this.isMenuSelected = isMenuSelected;
    }


    /**
     * Get the icon
     *
     * @return The icon or null if not set
     */
    public String getIcon ()
    {
        return this.icon;
    }


    /**
     * Get the name of the grid element.
     *
     * @return The name
     */
    public String getName ()
    {
        return this.name;
    }


    /**
     * Get the color to use in the header.
     *
     * @return The color or null if not set
     */
    public Color getColor ()
    {
        return this.color;
    }


    /**
     * Is the grid element seleted?
     *
     * @return True if selected
     */
    public boolean isSelected ()
    {
        return this.isSelected;
    }


    /**
     * Draws a text into a boundary. The text is clipped on the right border of the bounds.
     * Calculates the text descent.
     *
     * @param g The graphics context in which to draw
     * @param text The text to draw
     * @param x The x position of the boundary
     * @param y The y position of the boundary
     * @param width The width position of the boundary
     * @param height The height position of the boundary
     * @param alignment The alignment of the text: Label.LEFT or Label.CENTER
     */
    public static void drawTextInBounds (final GraphicsOutput g, final String text, final double x, final double y, final double width, final double height, final int alignment, final Color color)
    {
        drawTextInBounds (g, text, x, y, width, height, alignment, getTextDescent (g, "Hg"), color);
    }


    /**
     * Draws a text into a boundary. The text is clipped on the right border of the bounds.
     *
     * @param g The graphics context in which to draw
     * @param text The text to draw
     * @param x The x position of the boundary
     * @param y The y position of the boundary
     * @param width The width position of the boundary
     * @param height The height position of the boundary
     * @param alignment The alignment of the text: Label.LEFT or Label.CENTER
     * @param textDescent Text text descent
     */
    public static void drawTextInBounds (final GraphicsOutput g, final String text, final double x, final double y, final double width, final double height, final int alignment, final int textDescent, final Color color)
    {
        if (text == null || text.length () == 0)
            return;
        final Dimension dim = getTextDims (g, text);
        // TODO
        // g.clipRect (x, y, width, height);
        final double pos;
        switch (alignment)
        {
            case Label.LEFT:
                pos = x;
                break;

            case Label.CENTER:
            default:
                pos = x + (width - dim.width) / 2.0;
                break;
        }
        g.drawText (pos, y + height - (height - dim.height) / 2 - textDescent, text, color.getRed () / 255.0, color.getGreen () / 255.0, color.getBlue () / 255.0, color.getAlpha () / 255.0);
        // g.setClip (null);
    }


    /**
     * Draws a text centered into a height (horizontally). The text is not clipped.
     *
     * @param g The graphics context in which to draw
     * @param text The text to draw
     * @param x The x position of the boundary
     * @param y The y position of the boundary
     * @param height The height position of the boundary
     */
    public static void drawTextInHeight (final GraphicsOutput g, final String text, final double x, final double y, final double height, final Color color)
    {
        if (text == null || text.length () == 0)
            return;
        final Dimension dim = getTextDims (g, text);
        g.drawText (x, y + height - (height - dim.height) / 2 - getTextDescent (g, "Hg"), text, color.getRed () / 255.0, color.getGreen () / 255.0, color.getBlue () / 255.0, color.getAlpha () / 255.0);
    }


    /**
     * Get the width and the height of a text string.
     *
     * @param g The graphics context in which to draw
     * @param text The text to draw
     * @return The dimension of the text string
     */
    public static Dimension getTextDims (final GraphicsOutput g, final String text)
    {
        // TODO CAN ONLY BE CALLED DURING INIT
        // TextExtents textExtents = g.getTextExtents (text);

        // TODO
        // final FontMetrics fm = g.getFontMetrics ();
        // final Rectangle2D bounds = fm.getStringBounds (text, g);
        // final LineMetrics lm = fm.getFont ().getLineMetrics (text, g.getFontRenderContext ());
        // final double width = bounds.getWidth ();
        // bounds.setRect (bounds.getX (), bounds.getY (), width, lm.getHeight ());
        // return new Dimension ((int) Math.round (width), (int) Math.round (bounds.getHeight ()));

        // return new Dimension ((int) Math.round (textExtents.getWidth ()), (int) Math.round
        // (textExtents.getHeight ()));

        return new Dimension (100, 20);
    }


    /**
     * Get the distance from the text's baseline to its bottom edge.
     *
     * @param g The graphics context in which to draw
     * @param text The text to draw
     * @return The distance from the text's baseline to its bottom edge
     */
    public static int getTextDescent (final GraphicsOutput g, final String text)
    {
        // final float descent = g.getFont ().getLineMetrics (text, g.getFontRenderContext
        // ()).getDescent ();
        // return Math.round (descent);
        return 10;
    }


    /**
     * Draws a menu at the top of the element.
     *
     * @param gc The graphics context
     * @param left The left bound of the menus drawing area
     * @param width The width of the menu
     * @param layoutSettings The layout settings to use
     */
    protected void drawMenu (final GraphicsOutput gc, final double left, final double width, final LayoutSettings layoutSettings)
    {
        final Color borderColor = layoutSettings.getBorderColor ();
        if (this.menuName == null || this.menuName.length () == 0)
        {
            // Remove the 2 pixels of the previous menus border line
            setColor (gc, borderColor);
            gc.rectangle (left - SEPARATOR_SIZE, MENU_HEIGHT - 2, SEPARATOR_SIZE, 1);
            gc.fill ();
            return;
        }

        final Color textColor = layoutSettings.getTextColor ();

        // TODO
        setColor (gc, this.isMenuSelected ? textColor : borderColor);
        // fill
        gc.rectangle (left, 0, width, MENU_HEIGHT - 1.0);

        setColor (gc, textColor);
        // fill
        gc.rectangle (left, MENU_HEIGHT - 2.0, width + SEPARATOR_SIZE, 1);

        // setColor (gc, this.isMenuSelected ? borderColor : textColor);
        // TODO gc.setFont (layoutSettings.getTextFont (UNIT));
        drawTextInBounds (gc, this.menuName, left, 0, width, UNIT + SEPARATOR_SIZE, Label.CENTER, this.isMenuSelected ? borderColor : textColor);
    }


    /**
     * Get the maximum value range.
     *
     * @return The maximum value
     */
    public static double getMaxValue ()
    {
        return maxValue;
    }


    /**
     * Set the maximum value range. The default is 1024.0.
     *
     * @param maxValue The new maximum value
     */
    public static void setMaxValue (final double maxValue)
    {
        AbstractGridElement.maxValue = maxValue;
    }


    protected static void setColor (final GraphicsOutput gc, final Color color)
    {
        gc.setColor (color.getRed () / 255.0, color.getGreen () / 255.0, color.getBlue () / 255.0);
    }
}