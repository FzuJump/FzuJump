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

    public GameObject UI_Src;//������
    public GameObject UI_Sli;//������
    public LayerMask Ground;//����ͼ��
    public Collider2D Coll;

    void Start()
    {
        Rb = GetComponent<Rigidbody2D>();
        Coll = GetComponent<Collider2D>();

        Hide(UI_Src);//Ĭ�Ͽ�ʼʱ������������
    }

    void Update()
    {
        if (Coll.IsTouchingLayers(Ground)) //��������ͼ��
        {
            Slider_Move();

            if (Input.touchCount > 0)//����ָ�������Խ�磩
            {
                Slider_Stop();
                Scrollbar_Move_And_Stop();
            }
        }

        Show_Sli();
    }

    void Show(GameObject go)//UI��ʾ
    {
        go.GetComponent<CanvasGroup>().alpha = 1;
    }

    void Hide(GameObject go)//UI����
    {
        go.GetComponent<CanvasGroup>().alpha = 0;
    }

    void Slider_Move()//�������˶�
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

    void Slider_Stop()//������ֹͣ�ƶ�
    {
        if (Input.GetTouch(0).phase == TouchPhase.Began)
        //������Ļ
        {
            flag = false;
            Sli_Value = (float)(Sli.value - 0.5) * 2.0f;

            Show(UI_Src);
        }
    }

    void Scrollbar_Move_And_Stop()//��������ʼ��ֹͣ�ƶ�
    {
        if (Input.GetTouch(0).phase == TouchPhase.Stationary && !flag)
        //������Ļ����
        {
            Press_Time += Time.deltaTime;
            Scr.size = Press_Time / 2f;
        }

        if (Input.GetTouch(0).phase == TouchPhase.Ended && !flag)
        //�뿪��Ļ
        {
            Hide(UI_Src);
            Hide(UI_Sli);

            Sli.value = 0.5f;//���û�������ť������λ
            num = 1;//���û�������

            Scr_Value = Scr.size;
            Rb.AddForce((new Vector2(Sli_Value, Scr_Value)) * Press_Force * Press_Time, ForceMode2D.Impulse);
            Press_Time = 0f;
            flag = true;
        }
    }

    void Show_Sli()//������������
    {
        if (Coll.IsTouchingLayers(Ground) && Rb.velocity.x == 0)//�Ӵ�������ֹͣ�˶�
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
        if (Collision.tag == "Collection")//�����ཻ��
        {
            Destroy(Collision.gameObject);
        }
    }
}
