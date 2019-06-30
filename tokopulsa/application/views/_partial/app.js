function startApp() {
    //buat WebSocket
    var client = new WebSocket("ws://192.168.43.1:8989");

    client.onopen = function (event) {
      var log = document.getElementById("log");
      log.textContent = log.textContent + "\n" + "koneksi ke Server berhasil";
    };

    client.onclose = function (event){
      var log = document.getElementById("log");
      log.textContent = log.textContent + "\n" + "Koneksi ke server terputus";
    };

    client.onerror = function (event){
      var log = document.getElementById("log");
      log.textContent = log.textContent + "\n" + "Koneksi ke Server Error";
    };
  }

  window.onload = startApp;

  //aksi tombol send

  $("#send").on('click', function () {
  // mengambil value no tujuan

    //no tujuan
    var to = $('#to').val();

    //isi pesan
    var message = $("#message").val();

    var splits = to.split(",");
    if (splits.length == 1) {
      //bkn bc
      // membuat json
      var json = {
        to: splits[0],
        message: message
      };
      client.send(JSON.stringify(json));

    }else {
        //bc

        var json = {
          to: splits,
          message: message
        };

        client.send(JSON.stringify(json));
    }

  })

  // handler on Messages

  client.onmessage = function (event){
    var response = JSON.parse(event.data);

    switch (response.type) {
      case "success":
        //sms sukses
        alert(response.message);
        break;
      case "error":
        //sms gagal
        alert(response.message)
        break;
      case "notification":
        //laporan sms status SMS
        var log = document.getElementById("log");
        if (response.success) {
          log.textContent = log.textContent + "\n" +
           "Laporan sukses : " + response.message;
        }else {
          log.textContent = log.textContent + "\n" +
          "Laporan gagal : " + response.message;
        }
        break;
      case "received":
        if (confirm("SMS dari " + response.from + " : \n"
      + response.message + "\n" + "Apakah ingin dibalas?")) {
          document.getElementById("to").value = response.from;
        }
        break;
    }
  }