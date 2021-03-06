package edu.byu.cs.superasteroids.model.shipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewWidth;

/**
 * Created by audakel on 5/16/16.
 */
public class MainBody extends ShipPart {
    /**
     * Coordinates for atatching cannon picture to main picture
     */
    private PointF cannonAttach;
    /**
     * Coordinates for atatching engine picture to main picture
     */
    private PointF engineAttach;
    /**
     * Coordinates for atatching extra picture to main picture
     */
    private PointF extraAttach;

    /**
     * @param image       gameImage for shipPart
     * @param speed       defaults to 0, how fast item is moving
     * @param rotation    defaults to 0, rotation of item
     * @param position    defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale       scale of item to draw to - taken from game constants
     */
    public MainBody(GameImage image, int speed, float rotation, PointF position, PointF attachPoint, float scale,
                    PointF cannonAttach, PointF engineAttach, PointF extraAttach)
    {
        super(image, speed, rotation, position, attachPoint, scale);
        this.cannonAttach = cannonAttach;
        this.engineAttach = engineAttach;
        this.extraAttach = extraAttach;
    }

    /**
     * Construct an object from a json object
     * @param jsonObject
     * @throws JSONException
     */
    public MainBody(JSONObject jsonObject)  {
        super(jsonObject, null);
        try {
            this.cannonAttach = extractPointFromString(jsonObject.getString("cannonAttach"));
            this.engineAttach = extractPointFromString(jsonObject.getString("engineAttach"));
            this.extraAttach = extractPointFromString(jsonObject.getString("extraAttach"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PointF getPosition() {
        if (getGameDelegate() != null){

            setPosition(getGameDelegate().getShip().getPosition());
        }
        return position;
    }

    /**
     * Helper empty constructor
     */
    public MainBody()  {
        super(new GameImage(0,0,""), 0, 0, new PointF(getGameViewWidth() / 2, getGameViewWidth() / 2) , null, SCALE_FACTOR);
    }


    @Override
    public ContentValues getContentValues(){
        initContentValues();
        contentValues.put(Contract.MAIN_BODY_CANNON_ATTATCH, getCannonAttach().x + "," + getCannonAttach().y);
        contentValues.put(Contract.MAIN_BODY_ENGINE_ATTATCH, getEngineAttach().x + "," + getEngineAttach().y);
        contentValues.put(Contract.MAIN_BODY_EXTRA_ATTATCH, getExtraAttach().x + "," + getExtraAttach().y);

        return contentValues;
    }

    public PointF getCannonAttach() {
        return cannonAttach;
    }

    public void setCannonAttach(PointF cannonAttach) {
        this.cannonAttach = cannonAttach;
    }

    public PointF getEngineAttach() {
        return engineAttach;
    }

    public void setEngineAttach(PointF engineAttach) {
        this.engineAttach = engineAttach;
    }

    public PointF getExtraAttach() {
        return extraAttach;
    }

    public void setExtraAttach(PointF extraAttach) {
        this.extraAttach = extraAttach;
    }
}
