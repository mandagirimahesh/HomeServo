let cid = window.localStorage.getItem('id')
let name = window.localStorage.getItem('name')
let email = window.localStorage.getItem('email')
let phone = window.localStorage.getItem('phone')
let fc = window.localStorage.getItem('familycount')

let userid = document.getElementById('userid').innerHTML = cid
let username = document.getElementById('username').innerHTML = name
let useremail = document.getElementById('useremail').innerHTML = email
let userphone = document.getElementById('userphone').innerHTML = phone

let logout = document.getElementById('logout')
logout.addEventListener("click", () => {
    window.localStorage.clear()
    window.location.replace('http://127.0.0.1:5500/CustomerRegistration.html')
})

// delete ---------------------------------------------------------------------
let deleteuser = document.getElementById("userdelete")
deleteuser.addEventListener('click', () => {
    document.getElementById('pop').style.display = 'grid'
})
let canceldelete = document.getElementById('cancel-delete')
canceldelete.addEventListener('click', () => {
    document.getElementById('pop').style.display = 'none'
})
let dodelete = document.getElementById('do-delete')
dodelete.addEventListener("click", async () => {
    console.log('user deleted');
    await window.fetch(`http://localhost:8080/customer?id=${userid}`, {
        method: "DELETE"
    })
    window.localStorage.clear()
    window.location.replace('http://127.0.0.1:5500/CustomerRegistration.html')
})

// works list -----------------------------------------------------------------------------


async function worksList() {
    let awb = document.getElementById('awb')
    let cwb = document.getElementById('cwb')
    document.getElementById('cworks-list-div').style.display = 'flex'
    document.getElementById('cworks-list-div2').style.display = 'none'
    awb.style.borderBottom = '2px solid black'
    cwb.style.borderBottom = 'none'
    let works = await fetch('http://localhost:8080/findallworks')
    let worksjs = await works.json()
    // console.log(worksjs);
    document.getElementById('cworks-list-div').innerHTML = ''
    for (let i = 0; i < worksjs.data.length; i++) {
        if (worksjs.data[i].customer.id == localStorage.getItem('id')) {
            if (worksjs.data.length === 0) {
                let workdiv = document.getElementById('cworks-list-div')
                workdiv.innerHTML = `<h2>No works created</h2>`
            } else {
                // console.log(ele);
                // console.log(ele.type);
                let workdiv = document.getElementById('cworks-list-div')
                workdiv.innerHTML += `<div id="work-item-div">
            <div id="work-item-div2">
                <h2 id="g-work-name">${worksjs.data[i].type}</h2>
                <p id="g-work-desc">${worksjs.data[i].description}</p>
                <span id="g-work-id">work id : <span id="g-work-id2">${worksjs.data[i].id}</span></span>
            </div>
            <div id="item-address">
                <div id="id1"><span id="g-work-hno">${worksjs.data[i].address.d_no}</span>, <span id="g-work-street">${worksjs.data[i].address.street}</span>, <span id="g-work-landmark">${worksjs.data[i].address.landmark}</span></div>
                <div id="id2"><span id="g-work-district">${worksjs.data[i].address.district}</span>, <span id="g-work-state">${worksjs.data[i].address.state}</span></div>
                <div id="id3"><span id="g-work-pincode">${worksjs.data[i].address.pincode}</span>.</div>
            </div>
        </div>`
            }
        }
    }
}
worksList()

async function worksList2() {
    let awb = document.getElementById('awb')
    let cwb = document.getElementById('cwb')
    document.getElementById('cworks-list-div2').style.display = 'flex'
    document.getElementById('cworks-list-div').style.display = 'none'
    cwb.style.borderBottom = '2px solid black'
    awb.style.borderBottom = 'none'

    let works = await fetch('http://localhost:8080/completedworks')
    let worksjs = await works.json()
    document.getElementById('cworks-list-div2').innerHTML = ''

    for (let i = 0; i < worksjs.data.length; i++) {
        if (worksjs.data[i].customer.id == localStorage.getItem('id')) {

            if (worksjs.data.length === 0) {
                let workdiv = document.getElementById('cworks-list-div2')
                workdiv.innerHTML = `<h2>No works completed</h2>`
            } else {
                let workdiv = document.getElementById('cworks-list-div2')
                workdiv.innerHTML += `<div id="work-item-div">
                    <div id="work-item-div2">
                        <h2 id="g-work-name">${worksjs.data[i].type}</h2>
                        <p id="g-work-desc">${worksjs.data[i].description}</p>
                        <span id="g-work-id">work id : <span id="g-work-id2">${worksjs.data[i].id}</span></span>
                    </div>
                    <div id="item-address">
                        <div id="id1"><span id="g-work-hno">${worksjs.data[i].address.d_no}</span>, <span id="g-work-street">${worksjs.data[i].address.street}</span>, <span id="g-work-landmark">${worksjs.data[i].address.landmark}</span></div>
                        <div id="id2"><span id="g-work-district">${worksjs.data[i].address.district}</span>, <span id="g-work-state">${worksjs.data[i].address.state}</span></div>
                        <div id="id3"><span id="g-work-pincode">${worksjs.data[i].address.pincode}</span>.</div>
                    </div>
                    <button id="pay-work-btn" class="pay-work-btn" data-work-id="${worksjs.data[i].type}">Pay</button>
                </div>`;
            }
        }
    }

    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('pay-work-btn')) {
            const workId = event.target.getAttribute('data-work-id');
            console.log(workId);
        }
    });
}





// create work button----------------------------------------------------------------------

let creatework = document.getElementById('c-work-btn')
creatework.addEventListener("click", () => {
    document.getElementById('work-btn-div').style.display = 'none'
    document.getElementById('work-list-header').style.display = 'none'
    let works = document.getElementById('cworks-list-div')
    let cwork = document.getElementById('create-work')
    let display = works.classList.toggle('true')
    if (display) {
        works.style.display = 'none'
        cwork.style.display = 'flex'
        let cwbtn = document.getElementById('c-work-btn')
        cwbtn.value = 'Cancel'
    } else {
        document.getElementById('work-btn-div').style.display = 'inline'
        document.getElementById('work-list-header').style.display = 'grid'
        works.style.display = 'flex'
        cwork.style.display = 'none'
        let cwbtn = document.getElementById('c-work-btn')
        cwbtn.value = 'Create Work'
    }
})

//------   submit work ------------------------------------------------------------------------------------------

let cwbbtn = document.getElementById('cw-sub-btn')
cwbbtn.addEventListener("click", async (e) => {
    e.preventDefault()
    let workname = document.getElementById('cw-name')
    let dno = document.getElementById('cw-dno')
    let street = document.getElementById('cw-street')
    let landmark = document.getElementById('cw-landmark')
    let desc = document.getElementById('cw-desc')
    let pincode = await districtApi();

    if (workname.value === "") {
        let span = document.getElementById('cw-name-span')
        span.innerHTML = 'This field is Mandatory'
    } else if (dno.value === "") {
        let span = document.getElementById('cw-dno-span')
        span.innerHTML = 'This field is Mandatory'
    } else if (street.value === "") {
        let span = document.getElementById('cw-street-span')
        span.innerHTML = 'This field is Mandatory'
    } else if (landmark.value === "") {
        let span = document.getElementById('cw-landmark-span')
        span.innerHTML = 'This field is Mandatory'
    } else if (!pincode) {

    } else if (desc.value === "") {
        let span = document.getElementById('cw-desc-span')
        span.innerHTML = 'This field is Mandatory'
    } else {
        console.log('done');
        saveWork()
        let works = document.getElementById('cworks-list-div')
        let cwork = document.getElementById('create-work')
        works.style.display = 'flex'
        cwork.style.display = 'none'
        let cwbtn = document.getElementById('c-work-btn')
        cwbtn.value = 'Create Work'
    }

})

async function districtApi() {
    let inPin = document.getElementById("cw-pincode").value;
    if (inPin.length === 6) {
        let span = document.getElementById("cw-pincode-span");
        let dsspan = document.getElementById('cw-ds')

        try {
            let apidata = await window.fetch(
                "https://api.postalpincode.in/pincode/" + inPin
            );
            let apijson = await apidata.json();
            let status = apijson[0].Status;

            if (status === "Success") {
                dsspan.style.display = 'block'
                let districtTag = document.getElementById("cw-dist");
                let stateTag = document.getElementById("cw-state");
                var district = apijson[0].PostOffice[0].District;
                var state = apijson[0].PostOffice[0].State;


                span.style.display = "none";
                districtTag.innerHTML = district
                stateTag.innerHTML = state

                return { district: district, state: state }; // Pin code is valid
            } else {
                dsspan.style.display = 'none'
                span.style.display = "block";
                span.innerHTML = "Wrong PIN Code";
                span.style.color = "red";
                return false; // Pin code is invalid
            }
        } catch (error) {
            console.error("Error fetching pin code data:", error);
            return false; // Handle API error
        }
    } else {
        let dsspan = document.getElementById('cw-ds')
        dsspan.style.display = 'none'
        let span = document.getElementById("cw-pincode-span");
        span.style.display = "block";
        span.innerHTML = "Invalid PIN Code";
        span.style.color = "red";
        return false; // Invalid pin code format
    }
}
//-------------------------------------------------------
async function districtApiupdate() {
    let inPin = document.getElementById("cus-up-pincode").value;
    if (inPin.length === 6) {
        let dsdiv = document.getElementById("up-ds");
        let spand = document.getElementById("up-ds-d");
        let spans = document.getElementById("up-ds-s");

        try {
            let apidata = await window.fetch(
                "https://api.postalpincode.in/pincode/" + inPin
            );
            let apijson = await apidata.json();
            let status = apijson[0].Status;

            if (status === "Success") {
                dsdiv.style.display = 'flex'
                spand.style.display = 'block'
                spans.style.display = 'block'
                let districtTag = document.getElementById("up-ds-d");
                let stateTag = document.getElementById("up-ds-s");
                var district = apijson[0].PostOffice[0].District;
                var state = apijson[0].PostOffice[0].State;

                districtTag.innerHTML = district
                stateTag.innerHTML = state

                return { district: district, state: state }; // Pin code is valid
            } else {
                dsdiv.style.display = 'flex'
                spand.style.display = "block";
                spans.style.display = "none";
                spand.innerHTML = "Wrong PIN Code";
                spand.style.color = "red";
                return false; // Pin code is invalid
            }
        } catch (error) {
            console.error("Error fetching pin code data:", error);
            return false; // Handle API error
        }
    } else if (inPin.length === 0) {
        let dsdiv = document.getElementById("up-ds");
        dsdiv.style.display = 'none'
        return false;
    } else {
        let dsdiv = document.getElementById("up-ds");
        let spand = document.getElementById("up-ds-d");
        let spans = document.getElementById("up-ds-s");
        spans.style.display = 'none'
        dsdiv.style.display = 'flex'
        spand.innerHTML = "Invalid PIN Code";
        spand.style.color = "red";
        return false; // Invalid pin code format
    }
}
// ---- save work ------------------------------------------------------------------------------------------------------

async function saveWork() {
    let workname = document.getElementById('cw-name').value
    let dno = document.getElementById('cw-dno').value
    let street = document.getElementById('cw-street').value
    let landmark = document.getElementById('cw-landmark').value
    let pincode = document.getElementById('cw-pincode').value
    let desc = document.getElementById('cw-desc').value
    let ds = await districtApi()
    let district = ds.district
    let state = ds.state

    const workobj = {
        type: workname,
        address: {
            d_no: dno,
            street: street,
            landmark: landmark,
            pincode: pincode,
            district: district,
            state: state,
        },
        description: desc
    }

    const work = JSON.stringify(workobj)
    console.log(work);

    try {
        const response = await fetch(`http://localhost:8080/savework?cus_id=${cid}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: work,
        });



    } catch (error) {
        console.log("error", error);
    }
}

// ---- Update profile --------------------------------------------------------------------------------------------------

let upbtn = document.getElementById('userupdate')
upbtn.addEventListener("click", () => {
    let updiv = document.getElementById('cus-update')
    updiv.style.display = 'block'
})
let cancelupdate = document.getElementById('cancel-up')
cancelupdate.addEventListener("click", () => {
    let updiv = document.getElementById('cus-update')
    updiv.style.display = 'none'
})

// details update-------------------------------------------------------------------------------------------------------

let upsubbtn = document.getElementById('cus-up-btn')
upsubbtn.addEventListener("click", async () => {
    let cusname = document.getElementById('cus-up-name').value
    let cusemail = document.getElementById('cus-up-email').value
    let cusphone = document.getElementById('cus-up-phone').value
    let cuspsw = document.getElementById('cus-up-psw').value
    let cusfc = document.getElementById('cus-up-fc').value
    let cusaid = localStorage.getItem("aid")
    let cusdno = document.getElementById('cus-up-dno').value
    let cusstreet = document.getElementById('cus-up-street').value
    let cuslandmark = document.getElementById('cus-up-landmark').value
    let cuspincode = document.getElementById('cus-up-pincode').value
    let ds = await districtApiupdate();
    let cusdistrict = ds.district
    let cusstate = ds.state

    if (cusname === "")
        cusname = localStorage.getItem("name")
    else
        cusname = localStorage.setItem("name", cusname)
    if (cusemail === "")
        cusemail.value = localStorage.getItem("email")
    else
        cusemail = localStorage.setItem("email", cusemail)
    if (cusphone === "")
        cusphone = localStorage.getItem("phone")
    else
        cusphone = localStorage.setItem("phone", cusphone)
    if (cusfc === "")
        cusfc = localStorage.getItem("familycount")
    else
        cusfc = localStorage.setItem("familycount", cusfc)
    if (cusdno === "")
        cusdno = localStorage.getItem("dno")
    else
        cusdno = localStorage.setItem("dno", cusdno)
    if (cusstreet === "")
        cusstreet = localStorage.getItem("street")
    else
        cusstreet = localStorage.setItem("street", cusstreet)
    if (cuslandmark === "")
        cuslandmark = localStorage.getItem("landmark")
    else
        cuslandmark = localStorage.setItem("landmark", cuslandmark)
    if (cuspincode === "") {
        cuspincode = localStorage.getItem("pincode")
        cusdistrict = localStorage.getItem("district")
        cusstate = localStorage.getItem("state")
    } else {
        cuspincode = localStorage.setItem("pincode", cuspincode)
        cusdistrict = localStorage.setItem("district", cusdistrict)
        cusstate = localStorage.setItem("state", cusstate)
    }

    console.log(email);
    const cusupdateobj = {
        id: cid,
        name: localStorage.getItem("name"),
        email: localStorage.getItem("email"),
        phone: localStorage.getItem("phone"),
        pwd: cuspsw,
        familyCount: localStorage.getItem("familycount"),
        address: {
            id: cusaid,
            d_no: localStorage.getItem("dno"),
            street: localStorage.getItem("street"),
            landmark: localStorage.getItem("landmark"),
            pincode: localStorage.getItem("pincode"),
            district: localStorage.getItem("district"),
            state: localStorage.getItem("state")
        }
    }

    let cusupdate = JSON.stringify(cusupdateobj)


    // console.log(cusupdate);

    const resp = await fetch('http://localhost:8080/customer', {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: cusupdate,
    })

    let updone = document.getElementById('up-done')
    updone.style.display = 'block'


})