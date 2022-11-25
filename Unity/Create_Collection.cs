using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Create_Collection : MonoBehaviour
{
    public GameObject Collection;
    public Camera Main_Camera;
 
    void Start()
    {
       
    }

    void Update()
    {
        Vector3 screen3dPos = Main_Camera.WorldToScreenPoint(Collection.transform.position);
        if (screen3dPos.y < 0f)
        {
            Random_Create();
        }

    }
    private void OnTriggerEnter2D(Collider2D Collision)
    {
        if (Collision.tag == "Player")
        {
            Random_Create();
        }    
    }

    void Random_Create()
    {
        float x = Random.Range(Main_Camera.transform.position.x - 4, Main_Camera.transform.position.x + 4);
        float y = Random.Range(Main_Camera.transform.position.y + 15, Main_Camera.transform.position.y + 30);

        Instantiate(Collection, new Vector3(x, y, 0), transform.rotation);

        Destroy(Collection);
    }

}
