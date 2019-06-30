		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<!-- Card -->
					<div class="page-header">
						<h4 class="page-title">SMS Gateway</h4>
						<?php $this->load->view('_partial/breadcrumbs') ?>
					</div>
					<form method="post" action="<?php echo base_url('dashboard/tambah') ?>">	
						<div class="row">
							<div class="col-md-6">
								<div class="col-md-12">
									<div class="form-group">
										<label>No Tujuan : </label>
										<input type="text" id="to" name="to" class="form-control" required="required">
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>Pilih provider :</label>
										<select class="form-control" id="provider" name="provider" required="required">
											<option>TELKOMSEL</option>
											<option>XL Axiata</option>
											<option>Indosat Ooredoo</option>
											<option>3</option>
											<option>Axis</option>
											<option>Smartfren</option>
										</select>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>Nominal : </label>
										<select class="form-control" id="nominal" name="nominal" required="required">
											<option>10.000</option>
											<option>15.000</option>
											<option>20.000</option>
											<option>25.000</option>
											<option>50.000</option>
											<option>100.000</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-12">
									<div class="form-group">
										<label>Log Activity :</label>
										<textarea id="log" name="log" class="form-control" cols="5" rows="10"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-2 ml-auto mr-auto">
								<div class="form-group">
									<input type="submit" name="send" id="send" class="btn btn-primary btn-md" value="Send">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
				<?php $this->load->view('_partial/foot') ?>
    		<!-- <script type="text/javascript" src="app.js"/> -->
    		<script type="text/javascript">
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
			      //aksi tombol send

			      document.getElementById("send").onclick = function () {
			      // mengambil value no tujuan

			        //no tujuan
			        var to = document.getElementById("to").value;
			        window.alert(to);
			        //isi pesan
			        // var message = document.getElementById("message").value;
			        var nominal = document.getElementById("nominal").value;
			        var provider = document.getElementById("provider").value;
			        // window.alert(message);
			        var message = "Terimakasih, isi pulsa "+provider+" dengan nominal "+nominal+" telah berhasil !!!"

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

			      }

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
			  }

			  window.onload = startApp;
    		</script>
			<?php $this->load->view('_partial/script') ?>
		</div>

		</body>
		</html>
