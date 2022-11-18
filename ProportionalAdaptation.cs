using UnityEngine;
using System.Collections;

public class Test : MonoBehaviour {

    //基于iPhone13pro/iPhone14比例的单位高度
     float devHeight = 25.32f;
    //基于iPhone13pro/iPhone14比例的单位宽度
    float devWidth = 11.70f;

     // Use this for initialization
    void Start()
    {

         float screenHeight = Screen.height;
         Debug.Log("screenHeight = " + screenHeight);
        //摄像机的尺寸
         float orthographicSize = this.GetComponent<Camera>().orthographicSize;
         //宽高比
         float aspectRatio = Screen.width * 1.0f / Screen.height;
         //摄像机的单位宽度
         float cameraWidth = orthographicSize * 2 * aspectRatio;

         Debug.Log("cameraWidth = " + cameraWidth);
        //如果设备的宽度大于摄像机的宽度的时候  调整摄像机的orthographicSize
        if (cameraWidth < devWidth)
        {
            orthographicSize = devWidth / (2 * aspectRatio);
            Debug.Log("new orthographicSize = " + orthographicSize);
            this.GetComponent<Camera>().orthographicSize = orthographicSize;
        }

     }
}
