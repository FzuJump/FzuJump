using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour
{
    public Rigidbody2D Rb;
    public Scrollbar Scr;
    public Slider Sli;
    public float Scr_Value = 0f;
    public float Sli_Value = 0f;
    public float Press_Time = 0f;
    public float Press_Force = 1.0f;
    public bool flag = true;
    public int num = 1;

    public GameObject UI_Src;//蓄力条
    public GameObject UI_Sli;//滑动条
    public LayerMask Ground;//地面图层
    public Collider2D Coll;

    void Start()
    {
        Rb = GetComponent<Rigidbody2D>();
        Coll = GetComponent<Collider2D>();

        Hide(UI_Src);//默认开始时蓄力条不出现
    }

    void Update()
    {
        if (Coll.IsTouchingLayers(Ground)) //触碰地面图层
        {
            Slider_Move();

            if (Input.touchCount > 0)//无手指点击（防越界）
            {
                Slider_Stop();
                Scrollbar_Move_And_Stop();
            }
        }

        Show_Sli();
    }

    void Show(GameObject go)//UI显示
    {
        go.GetComponent<CanvasGroup>().alpha = 1;
    }

    void Hide(GameObject go)//UI隐藏
    {
        go.GetComponent<CanvasGroup>().alpha = 0;
    }

    void Slider_Move()//滑动条运动
    {
        if (flag)
        {
            if (num % 2 != 0)
            {
                Sli.value += Time.deltaTime;
            }
            if (Sli.value >= 1 || Sli.value <= 0)
            {
                num++;
            }
            if (num % 2 == 0)
            {
                Sli.value -= Time.deltaTime;
            }
        }
    }

    void Slider_Stop()//滑动条停止移动
    {
        if (Input.GetTouch(0).phase == TouchPhase.Began)
        //按下屏幕
        {
            flag = false;
            Sli_Value = (float)(Sli.value - 0.5) * 2.0f;

            Show(UI_Src);
        }
    }

    void Scrollbar_Move_And_Stop()//蓄力条开始和停止移动
    {
        if (Input.GetTouch(0).phase == TouchPhase.Stationary && !flag)
        //按下屏幕不动
        {
            Press_Time += Time.deltaTime;
            Scr.size = Press_Time / 2f;
        }

        if (Input.GetTouch(0).phase == TouchPhase.Ended && !flag)
        //离开屏幕
        {
            Hide(UI_Src);
            Hide(UI_Sli);

            Sli.value = 0.5f;//重置滑动条滑钮至中心位
            num = 1;//重置滑动方向

            Scr_Value = Scr.size;
            Rb.AddForce((new Vector2(Sli_Value, Scr_Value)) * Press_Force * Press_Time, ForceMode2D.Impulse);
            Press_Time = 0f;
            flag = true;
        }
    }

    void Show_Sli()//滑动条的显隐
    {
        if (Coll.IsTouchingLayers(Ground) && Rb.velocity.x == 0)//接触地面且停止运动
        {
            Show(UI_Sli);
        }
        else
        {
            Hide(UI_Sli);
        }
    }

    private void OnTriggerEnter2D(Collider2D Collision)
    {
        if (Collision.tag == "Collection")//道具类交互
        {
            Destroy(Collision.gameObject);
        }
    }
}
