//以下仅为增加、修改部分
using static System.Runtime.CompilerServices.RuntimeHelpers;

public class PlayerController : MonoBehaviour//在PlayerController类中
{
    public bool IsDoublePressForce = false;//新增对pressForce翻倍道具的判断位
    void ScrollbarMaS()//在该方法
    {
        if (Input.GetKeyUp(KeyCode.W))//在该条件
        {
            if (IsDoublePressForce)//新增对pressForce翻倍道具失效的语句
            {
                IsDoublePressForce = false;
                pressForce /= 2;
            }
        }
    }
}
