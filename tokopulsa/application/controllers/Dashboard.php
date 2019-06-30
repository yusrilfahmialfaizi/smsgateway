<?php 
defined('BASEPATH') OR exit('No direct script access allowed');
	/**
	  * 
	  */
	 class Dashboard extends CI_Controller
	 {
	 	
	 	function __construct()
	 	{
	 		# code...
	 		parent::__construct();
	 		$this->load->model("Dashboardmodel");
	 	}
	 	public function index()
 		{
 			// $data['barang'] = $this->Barangmodel->getMerek(); 
			$this->load->view('_partial/headerfront');
			$this->load->view('frontend');
			// $this->load->view('_partial/footer');
		}
		function tambah()
		{
			$ke = $this->input->post('to');
			$provider = $this->input->post('provider');
			$nominal = $this->input->post('nominal');
			$tgl = date('Y-m-d H:i:s');
			$query = $this->Dashboardmodel->kirim($ke,$provider,$nominal,$tgl);
			
				redirect('dashboard');
				# code...
			
		}

	 } 
 ?>