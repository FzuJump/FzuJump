using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Show_Height : MonoBehaviour
{
    public Rigidbody2D Rb;
    public LayerMask Ground;//����ͼ��
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
        if (Rb.velocity.y == 0)//����ż���߶�
        {
            y1 = Rb.transform.position.y;
            if (y1 > y0)//������Ч�߶�
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
            UI_Height.text = "���ѵ���4��\n���Լ�Ǹ��ݴ�ѧһ������¥�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 7)
        {
            UI_Height.text = "���ѵ���7��\n���Լ�Ǹ��ݴ�ѧһ��ʳ�õĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 15)
        {
            UI_Height.text = "���ѵ���15��\n���Լ�Ǹ��ݴ�ѧ���Ѹ�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 20)
        {
            UI_Height.text = "���ѵ���20��\n���Լ�Ǹ��ݴ�ѧ��¥�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 23)
        {
            UI_Height.text = "���ѵ���23��\n���Լ�Ǹ��ݴ�ѧ����ѧԺ¥�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 25)
        {
            UI_Height.text = "���ѵ���25��\n���Լ�Ǹ��ݴ�ѧ�����������Ƚ�ѧ¥�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 27)
        {
            UI_Height.text = "���ѵ���27��\n���Լ�Ǹ��ݴ�ѧ����¥�ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 70)
        {
            UI_Height.text = "���ѵ���70��\n���Լ�Ǹ��ݴ�ѧ����¥���о�������¥���ĸ߶�";
            Show();
            Invoke("Hide", 5);
        }
        if (Height > 100)
        {
            UI_Height.text = "���ѵ���100��\n���ѵ��︣�ݴ�ѧУ�ڳ���ɽ�ĺ��θ߶�";
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