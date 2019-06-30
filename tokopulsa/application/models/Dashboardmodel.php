<?php 
	/**
	 * 
	 */
	class Dashboardmodel extends CI_Model
	{
		
		function __construct()
		{
			# code...
			parent::__construct();
		}

		function kirim($ke,$provider,$nominal,$tgl)
		{
			
			$data = array(
				'no_tujuan'		=> $ke, 
				'provider'		=> $provider,
				'nominal'		=> $nominal,
				'tanggal'		=> $tgl
				);

			$this->db->insert("transaksi", $data);
		}
	}
?>