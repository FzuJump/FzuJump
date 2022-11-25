using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Show_Height : MonoBehaviour
{
    public Rigidbody2D Rb;
    public LayerMask Ground;//地面图层
    public Collider2D Coll;
    //public GameObject UI_Height;
    public Text UI_Height;
    public float Height;
    public float y0, y1;
    public bool flag = false;
    void Start()
    {
        Rb = GetComponent<Rigidbody2D>();
        Coll = GetComponent<Collider2D>();

        Height = 0;
        Hide();
    }

    void Update()
    {
        if (!flag)
        {
            if (Coll.IsTouchingLayers(Ground))
            {
                y0 = Rb.transform.position.y;
                flag = true;
            }
        }

        Calculate_Height();
    }

    void FixedUpdate()
    {     
        Show_Text();
    }

    void Calculate_Height()
    {
        if (Rb.velocity.y == 0)//落板后才计算高度
        {
            y1 = Rb.transform.position.y;
            if (y1 > y0)//计算有效高度
            {
                Height += (y1 - y0)/10f;
                y0 = y1;
            }      
        }
    }

    void Show_Text()
    {
        if (Height > 4)
        {
            UI_Height.text = "您已到达4米\n这大约是福州大学一层宿舍楼的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 7)
        {
            UI_Height.text = "您已到达7米\n这大约是福州大学一层食堂的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 15)
        {
            UI_Height.text = "您已到达15米\n这大约是福州大学福友阁的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 20)
        {
            UI_Height.text = "您已到达20米\n这大约是福州大学文楼的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 23)
        {
            UI_Height.text = "您已到达23米\n这大约是福州大学数计学院楼的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 25)
        {
            UI_Height.text = "您已到达25米\n这大约是福州大学东三，西三等教学楼的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 27)
        {
            UI_Height.text = "您已到达27米\n这大约是福州大学嘉锡楼的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 70)
        {
            UI_Height.text = "您已到达70米\n这大约是福州大学晋江楼（研究生科研楼）的高度";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 100)
        {
            UI_Height.text = "您已到达100米\n这已到达福州大学校内长湾山的海拔高度";
            Show();
            Invoke("Hide", 5);
        }
    }

    void Show()
    {
        UI_Height.GetComponent<CanvasGroup>().alpha = 1;
    }

    void Hide()
    {
        UI_Height.GetComponent<CanvasGroup>().alpha = 0;
    }
}