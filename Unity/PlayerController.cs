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
    public bool Do_Slider_Stop = false;
    public bool Do_Scrollbar_Stop = false;

    public GameObject UI_GameOver;
    public GameObject UI_Live;
    public GameObject UI_Power;
    public bool Is_Double_Press_Force = false;
    public bool Is_Go_Up = false;
    private float Go_Up_Speed = 8f;
    private float Go_Up_Time = 0f;
    private float Go_Up_Distance = 20f;
    public float Go_Up_Horizontal_Speed = 15f;
    public Transform Trans;
    public int Life = 0;
    public GameObject Live_Platform;//复活平台
    public bool Is_Dead = false;
    public Camera Main_Camera;//主摄像机

    void Start()
    {
        Rb = GetComponent<Rigidbody2D>();
        Coll = GetComponent<Collider2D>();
        Trans = GetComponent<Transform>();

        Hide(UI_Src);//默认开始时蓄力条不出现
        Hide(UI_GameOver);//默认开始时游戏结束界面不出现
        Hide(UI_Live);//默认开始时复活指示不出现
        Hide(UI_Power);//默认开始时力量指示面不出现
        Live_Platform.SetActive(false);//默认开始时无复活平台
    }

    void Update()
    {
        if (Coll.IsTouchingLayers(Ground)) //触碰地面图层
        {
            Slider_Stop();
            Scrollbar_Move_And_Stop();
        }

        Show_UI_Live();
        Show_UI_Power();

        if(Is_Dead)
        {
            Live();
        }
    }

    private void FixedUpdate()
    {
        if (Coll.IsTouchingLayers(Ground)) //触碰地面图层
        {
            Slider_Move();

            if (Do_Slider_Stop == true)
            {
                Show(UI_Src);
            }

            if (Do_Scrollbar_Stop == true)
            {
                Hide(UI_Src);
                Hide(UI_Sli);

                Jump();

                Clear_Press_Force();
            }
        }

        Show_Sli();

        Go_Up();
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
        if (Input.GetKeyDown(KeyCode.W))
        //按下屏幕
        {
            flag = false;
            Sli_Value = (float)(Sli.value - 0.5) * 2.0f;

            Do_Slider_Stop = true;
        }
    }

    void Scrollbar_Move_And_Stop()//蓄力条开始和停止移动
    {
        if (Input.GetKey(KeyCode.W) && !flag)
        //按下屏幕不动
        {
            if (Scr.size < 1)
            {
                Press_Time += Time.deltaTime;
                Scr.size = Press_Time / 2f;
            }
        }

        if (Input.GetKeyUp(KeyCode.W) && !flag)
        //离开屏幕
        {
            Scr_Value = Scr.size;

            Do_Scrollbar_Stop = true;
        }
    }

    void Show_Sli()//滑动条的显隐
    {
        if (Coll.IsTouchingLayers(Ground) && Rb.velocity.x == 0)//接触地面且停止运动
        {
            Show(UI_Sli);

            Do_Slider_Stop = false;
            Do_Scrollbar_Stop = false;
            
        }
    }

    void Jump()
    {
        Rb.AddForce((new Vector2(Sli_Value, Scr_Value)) * Press_Force * Press_Time, ForceMode2D.Impulse);

        Sli.value = 0.5f;//重置滑动条滑钮至中心位
        num = 1;//重置滑动方向
        Press_Time = 0f;
        Scr.size = 0f;
        flag = true;
    }

    private void OnTriggerEnter2D(Collider2D Collision)
    {
        if (Collision.tag == "UnderBorder")//掉出下边界
        {
            Live();
        }
    }

    void Clear_Press_Force()
    {
        if (Is_Double_Press_Force)//新增对pressForce翻倍道具失效的语句
        {
            Is_Double_Press_Force = false;
            Press_Force /= 2;
        }
    }//去除双倍力

    void Go_Up()//实现垂直上升一段距离
    {
        if (Is_Go_Up)
        {
            Go_Up_Time += Time.deltaTime;
            Rb.velocity = new Vector2(0, Go_Up_Speed);

            if (Input.GetKey(KeyCode.D))
            {
                Trans.Translate(Vector2.right * Time.deltaTime * Go_Up_Horizontal_Speed);
            }
            if (Input.GetKey(KeyCode.A))
            {
                Trans.Translate(Vector2.left * Time.deltaTime * Go_Up_Horizontal_Speed);
            }
        }

        if (Go_Up_Time * Go_Up_Speed >= Go_Up_Distance)
        {
            Is_Go_Up = false;
            Go_Up_Time = 0f;
        }
    }

    void Live()
    {
        Life--;
        Time.timeScale = 0;

        if (Life < 0)
        {
            Show(UI_GameOver);
        }
        else
        {
            Live_Platform.transform.position = new Vector3(Main_Camera.transform.position.x, Main_Camera.transform.position.y -3, 0);//移动复活平台
            Live_Platform.SetActive(true);//生成复活平台
            transform.position = new Vector3(Main_Camera.transform.position.x, Main_Camera.transform.position.y, 0);//移动人物
            Time.timeScale = 1;
        }
    }

    void Show_UI_Live()
    {
        if (Life > 0)
        {
            Show(UI_Live);
        }
        else
        {
            Hide(UI_Live);
        }
    }
    
    void Show_UI_Power()
    {
        if (Is_Double_Press_Force)
        {
            Show(UI_Power);
        }
        else
        {
            Hide(UI_Power);
        }
    }
}
