package com.blitz.leprosydiagnosis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class MoreInfo extends Activity{


	int category;
	Intent intent;
	TextView tvMain,tvMoreInfoWeb, tvInfo,tvCauses,tvSigns,tvCatching,tvDiagnosis,tvTreatment,tvPrevention,tvUnderstanding;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moreinfo);

		try
		{
			intent = getIntent();
			category = intent.getIntExtra("category",1);
			System.out.println("Category "+category);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}

		tvMain = (TextView)findViewById(R.id.tvMainMore);
		tvInfo = (TextView)findViewById(R.id.tvMoreInfo);
		tvMoreInfoWeb = (TextView)findViewById(R.id.tvMoreInfoWeb);

		switch(category)
		{
		case 1:
			tvMain.setText("Causes");
			tvInfo.setText("A germ, or bacteria, called Mycobacterium leprae. It causes an infection that affects the skin, destroys nerves and can also cause problems in the eyes and nose.");
			tvMoreInfoWeb.setText("http://www.webmd.com/skin-problems-and-treatments/guide/leprosy-symptoms-treatments-history");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);

			break;
		case 2: 
			tvMain.setText("Signs");
			tvInfo.setText("Early signs include spots on the skin that may be slightly red, darker or lighter than normal skin. The spots may also become numb and have lost hair. Often they appear on the arms, legs or back. Sometimes the only sign may be numbness in a finger or toe. If left untreated, hands can become numb and small muscles are paralyzed, leading to curling of the fingers and thumb. When leprosy attacks nerves in the legs, it interrupts communication of sensation in the feet. The feet can then be damaged by untended wounds and infection. If the facial nerve is affected, a person loses the blinking reflex of the eye, which can eventually lead to dryness, ulceration and blindness. Bacteria entering the mucous lining of the nose can lead to internal damage and scarring which in time causes the nose to collapse. Untreated, leprosy can cause deformity, crippling and blindness.");
			tvMoreInfoWeb.setText("http://www.webmd.com/skin-problems-and-treatments/guide/leprosy-symptoms-treatments-history");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			break;
		case 3:
			tvMain.setText("Catching Leprosy");
			tvInfo.setText("M. leprae is transmitted primarily through coughing and sneezing. In most cases, it is spread through long-term contact with a person who has the disease but has not been treated. Scientists don’t fully understand how leprosy is spread.");
			tvMoreInfoWeb.setText("http://www.leprosy.org/leprosy-faqs/");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			break;
		case 4:
			tvMain.setText("Diagnosis");
			tvInfo.setText("A trained health worker diagnoses leprosy through a skin biopsy. In this test, a small piece of skin is taken and sent to a laboratory where it is examined for the bacteria. Skin smears are another test that can be used. This is done by making a small incision into the skin. A small amount of tissue fluid is obtained and examined in the laboratory for the bacteria. There are no blood tests for leprosy.");
			tvMoreInfoWeb.setText("http://www.leprosy.org/leprosy-faqs/");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			break;
		case 5:
			tvMain.setText("Treatment");
			tvInfo.setText("Treatment depends on the type of leprosy that you have. Antibiotics are used to treat the infection. Long-term treatment with two or more antibiotics is recommended, usually from six months to a year. People with severe leprosy may need to take antibiotics longer. Antibiotics cannot treat the nerve damage. \n Rifampicin and clofazimine are now combined with dapsone to treat multibacillary leprosy. A single dose of combination therapy has been used to cure single lesion paucibacillary leprosy: rifampicin (600 mg), ofloxacin (400 mg), and minocycline (100 mg).");
			tvMoreInfoWeb.setText("http://www.leprosy.org/leprosy-faqs/");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			break;
		case 6:
			tvMain.setText("Prevention");
			tvInfo.setText("The prevention of leprosy ultimately lies in the early diagnosis and treatment of those individuals suspected or diagnosed as having leprosy, thereby preventing further transmission of the disease to others.");
			tvMoreInfoWeb.setText("http://www.leprosy.org/leprosy-faqs/");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			break;
		case 7:
			tvMain.setText("Understanding it ");
			tvInfo.setText("Leprosy can be cured. In the last two decades, more than 14 million people with leprosy have been cured. The World Health Organization provides free treatment for all people with leprosy.");
			tvMoreInfoWeb.setText("http://www.leprosy.org/leprosy-faqs/");
			Linkify.addLinks(tvMoreInfoWeb , Linkify.WEB_URLS);
			
			break;

		}


	}

}
