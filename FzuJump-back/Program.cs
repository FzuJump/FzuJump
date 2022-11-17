//以下仅为增加、修改部分
using static System.Runtime.CompilerServices.RuntimeHelpers;

public class PlayerController : MonoBehaviour//在PlayerController类中
{
    public bool IsDoublePressForce = false;//新增有关pressForce道具效果的判断位
    void ScrollbarMaS()//在ScrollbarMas方法中
    {
        if (Input.GetKeyUp(KeyCode.W))//在该条件下
        {
            if (IsDoublePressForce)//增加道具使用过后失效的语句
            {
                IsDoublePressForce = false;
                pressForce /= 2;
            }
        }
    }
}
