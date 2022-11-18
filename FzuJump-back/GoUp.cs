using System.Numerics;

public class PlayerController : MonoBehaviour//在该类
{
    //新增以下变量
    public bool Is_Go_Up = false;
    public float Go_Up_Speed = 8f;
    public float Go_Up_Time = 0f;
    public float Go_Up_Distance = 20f;
    void Update()
    {
        GoUp();//新增该调用语句
    }
    void GoUp()//实现垂直上升一段距离
    {
        if (Is_Go_Up)
        {
            Go_Up_Time += Time.deltaTime;
            rb.velocity = new Vector2(0, Go_Up_Speed);
        }

        if (Go_Up_Time * Go_Up_Speed >= Go_Up_Distance)
        {
            Is_Go_Up = false;
            Go_Up_Time = 0f;
        }
    }
}
