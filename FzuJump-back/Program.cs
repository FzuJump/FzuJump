//以下仅为增加、修改部分
using static System.Runtime.CompilerServices.RuntimeHelpers;

public class PlayerController : MonoBehaviour//在PlayerController类中
{
    void ScrollbarMaS()//在该类中
    {
        if (Input.GetKey(KeyCode.W))//在该条件下
        {
            if (xulitiao.size < 1)//增加对蓄力条是否已满的判断
            {
                presstime += Time.deltaTime;
                xulitiao.size = presstime / 2f;
            }

        }
    }
}
