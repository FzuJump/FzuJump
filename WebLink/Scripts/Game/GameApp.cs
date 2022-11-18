using UnityEngine;
using UnityEngine.Networking;
using System.Collections;

public class GameApp : MonoBehaviour
{
    public void EnterGame()
    {
        //this.StartCoroutine(this.getBaidu());
        //this.StartCoroutine(this.GetUploadData());
        //this.StartCoroutine(this.ReadVersion());
        //this.StartCoroutine(this.DownFile());
        this.StartCoroutine(this.Upload());
    }

   

    IEnumerator ReadVersion()
    {
        UnityWebRequest req = UnityWebRequest.Get("http://127.0.0.1:6080/version.txt");
        yield return req.SendWebRequest();
        Debug.Log(req.downloadHandler.text);
        yield break;
    }

    IEnumerator getBaidu()
    {
        UnityWebRequest req = UnityWebRequest.Get("http://www.baidu.com");
        yield return req.SendWebRequest();
        Debug.Log("Success");
        Debug.Log(req.downloadHandler.text);
    }

    IEnumerator GetUploadData()
    {
        UnityWebRequest req = UnityWebRequest.Get("http://127.0.0.1:6080/uploadData?username=blake&upwd=123456");
        yield return req.SendWebRequest();
        Debug.Log(req.downloadHandler.text);
    }

    IEnumerator Upload()
    {
        WWWForm form = new WWWForm();
        form.AddField("myField", "myData");

        UnityWebRequest req = UnityWebRequest.Post("http://49.234.32.149/", form);
        yield return req.SendWebRequest();
        Debug.Log(req.downloadHandler.text);
    }

}
