using UnityEngine;

public class PlayerController : MonoBehaviour//�ڸ���
{
    //�������±���
    public bool Is_Go_Up = false;
    public float Go_Up_Speed = 8f;
    public float Go_Up_Time = 0f;
    public float Go_Up_Distance = 20f;
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
        }

        if (Go_Up_Time * Go_Up_Speed >= Go_Up_Distance)
        {
            Is_Go_Up = false;
            Go_Up_Time = 0f;
        }
    }
}
