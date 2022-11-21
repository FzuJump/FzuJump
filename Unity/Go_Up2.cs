using UnityEngine;

public class PlayerController : MonoBehaviour//�ڸ���
{
    //�������±���
    public bool Is_Go_Up = false;
    public float Go_Up_Speed = 8f;
    public float Go_Up_Time = 0f;
    public float Go_Up_Distance = 20f;
    public float Go_Up_Horizontal_Speed = 15f;
    public Transform trans;
    void Start()//��start
    {
        trans = GetComponent<Transform>();
    }
    void Update()//��update
    {
        GoUp();//���ø÷���
    }
    void GoUp()//��ֱ����һ�ξ������Ч��
    {
        if (Is_Go_Up)
        {
            Go_Up_Time += Time.deltaTime;
            rb.velocity = new Vector2(0, Go_Up_Speed);
            if (Input.GetKey(KeyCode.D))
            {
                trans.Translate(Vector2.right * Time.deltaTime * Go_Up_Horizontal_Speed);
            }
            if (Input.GetKey(KeyCode.A))
            {
                trans.Translate(Vector2.left * Time.deltaTime * Go_Up_Horizontal_Speed);
            }
        }

        if (Go_Up_Time * Go_Up_Speed >= Go_Up_Distance)
        {
            Is_Go_Up = false;
            Go_Up_Time = 0f;
        }
    }
}