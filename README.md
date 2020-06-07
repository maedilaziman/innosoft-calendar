# About
innosoft-calendar aims to help produce an easily usable implementation of create beauty calendar.<br/>
<br/>
<br/>
[![](https://jitpack.io/v/maedilaziman/innosoft-calendar.svg)](https://jitpack.io/#maedilaziman/innosoft-calendar)
</br>
<br/>
<h2>Dependency</h2>
Add this in your root build.gradle file (not your module build.gradle file):
<br/>
<br/>
<pre><span class="pl-en">allprojects</span> {
	repositories {
        maven { url <span class="pl-s"><span class="pl-pds">"</span>https://jitpack.io<span class="pl-pds">"</span></span> }
    }
}</pre>
Then, add the library to your module build.gradle
<br/>
<br/>
<pre><span class="pl-en">dependencies</span> {
    implementation <span class="pl-s"><span class="pl-pds">'</span>com.github.maedilaziman:innosoft-calendar:1.0.0<span class="pl-pds">'</span></span>
}</pre>
Release 1.0.1
</br>
</br>
<pre><span class="pl-en">dependencies</span> {
    implementation <span class="pl-s"><span class="pl-pds">'</span>com.github.maedilaziman:innosoft-calendar:1.0.1<span class="pl-pds">'</span></span>
}</pre>
<h2>Features</h2>
<ul>
<li>Create beauty calendar</li>
</ul>
<h2>Features release 1.0.1</h2>
Change calendar design
<br/>
<h2>Usage</h2>
add this code on your activity,
and call this initialCalendar.resetCalendar(); for show calendar.
<br/>
<br/>
<pre>private InitalCalendar initialCalendar;<br /><br />private InitalCalendar.CommInitialCalendar listenerCalendar;<br /><br />
@Override<br />protected void onCreate(Bundle savedInstanceState) {<br />    super.onCreate(savedInstanceState);
  initialCalendar = new InitalCalendar();<br />   initialCalendar.initView(null, f);<br />  
   initialCalendar.initCalendar();<br />   listenerCalendar = new InitalCalendar.CommInitialCalendar() {<br />    @Override<br />    public void resMap(Map m) {<br />        String dateStr = (String) m.get("data1");<br />        String schedule = (String) m.get("data2");<br />        Toast.makeText(f, dateStr, Toast.LENGTH_LONG).show();<br />    }<br /><br />    @Override<br />    public void setData() {<br /><br />    }<br /><br />    @Override<br />    public void hideView() {<br />        viewBeautyCalendar.setVisibility(View.GONE);<br />    }<br /><br />    @Override<br />    public void showView() {<br />        viewBeautyCalendar.setVisibility(View.VISIBLE);<br />    }<br /><br />    @Override<br />    public void startAnim(Animation animation) {<br />        viewBeautyCalendar.startAnimation(animation);<br />    }<br />  };<br />  initialCalendar.setListener(listenerCalendar);<br /><br />  anyButton.setOnClickListener(new View.OnClickListener() {<br />          @Override<br />          public void onClick(View v) {<br />              initialCalendar.resetCalendar();<br />          }<br />  });
<br />}</pre>
<br/>
That's it!
<br/>
<h2>License</h2>
<pre><code>Copyright 2020 Maedi Laziman
<br/>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
<br/>
   http://www.apache.org/licenses/LICENSE-2.0
<br/>
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.</code></pre>
