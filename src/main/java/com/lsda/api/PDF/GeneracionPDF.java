/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.PDF;

import com.lsda.api.entities.Detalle;
import com.lsda.api.entities.Empresa;
import com.lsda.api.entities.Encabezado;
import com.lsda.api.entities.Factura;
import com.lsda.api.entities.Impuesto;
import com.lsda.api.entities.Cargo;
import com.lsda.api.entities.Resoluciones;
import com.lsda.api.repositories.EmpresaRepository;
import com.lsda.api.repositories.FacturaRepository;
import com.lsda.api.repositories.ResolucionesRepository;

import com.lsda.plantilla.CreacionRepresentacion;
import static com.lsda.plantilla.CreacionRepresentacion.RUTA_BASE_IMAGENES;
import static com.lsda.plantilla.CreacionRepresentacion.RUTA_BASE_PDF;
import com.lsda.plantilla.EstrellaAndina;
import com.lsda.plantilla.GastronomiaItaliana;
import com.lsda.plantilla.CrepesWaffles;
import com.lsda.plantilla.Maquite;
import com.lsda.plantilla.CREPES_CARIBE;
import com.lsda.plantilla.CREPES_CARTAGENA;
import com.lsda.plantilla.HRC;
import com.lsda.plantilla.BandidoBristro;
import com.lsda.plantilla.Entrepues;
import com.lsda.plantilla.Pescadero_GIL;
import com.lsda.plantilla.Hanks_Hamburguesa;
import com.lsda.plantilla.Herencia;
import com.lsda.plantilla.LaCien;
import com.lsda.plantilla.Oliveto;
import com.lsda.plantilla.LPQ;
import com.lsda.plantilla.INMACULADA_GUADALUPE;
import com.lsda.plantilla.GauFoods;
import com.lsda.plantilla.FoodInc;
import com.lsda.plantilla.OperadorFranquiciasCol;
import com.lsda.plantilla.METRO_OPERACION_INMOBILIARIA;
import com.lsda.plantilla.Dondoh;
import com.lsda.plantilla.CartelSushi;
import com.lsda.plantilla.CAFE_SANTA_BARBARA;
import com.lsda.plantilla.KoAsian;
import com.lsda.plantilla.Monstruo;
import com.lsda.plantilla.CUCINARE;
import com.lsda.plantilla.GL5_LaSubienda;
import com.lsda.plantilla.Pardo_LaHuertana;
import com.lsda.plantilla.Abasto;
import com.lsda.plantilla.Insurgentes;
import com.lsda.plantilla.Pastordel7;
import com.lsda.plantilla.Mera_Medellin;
import com.lsda.plantilla.Mercari;
import com.lsda.plantilla.HomeBurgers;
import com.lsda.plantilla.FloraChapinero;
import com.lsda.plantilla.Ax2Gourmet;
import com.lsda.plantilla.Mera_Dorado;
import com.lsda.plantilla.Spoleto;
import com.lsda.plantilla.VILLA_NUESTRA_SENORA_CANDELARIA;
import com.lsda.plantilla.SU_PRESENCIA;
import com.lsda.plantilla.BKAL_Burger;
import com.lsda.plantilla.BKCOL_Burger;
import com.lsda.plantilla.KINCO_Burger;
import com.lsda.plantilla.PCA_Mimos;
import com.lsda.plantilla.DOMIORIENTE_Dominos;
import com.lsda.plantilla.FOOD_GROUP_COLOMBIA_Dominos;
import com.lsda.plantilla.GRUPO_EMPRESARIAL_PANAL_Dominos;
import com.lsda.plantilla.RyB_INVESTMENTS_Dominos;
import com.lsda.plantilla.INVERSIONES_GASTRONOMICAS_JASNABI_Andres;
import com.lsda.plantilla.LA_DIVINA_COMEDIA_Andres;
import com.lsda.plantilla.LOS_AMIGOS_DEL_221_Andres;
import com.lsda.plantilla.RESTAURANTE_EVENTOS_MI_MARGARITA;
import com.lsda.plantilla.DARI_FROST_HELADOS_COLOMBIA;
import com.lsda.plantilla.MATUNA_INVERSIONES_Gaira;
import com.lsda.plantilla.THE_MONKEY_HOUSE;
import com.lsda.plantilla.INVERSIONES_JUANFE_Kokoriko;
import com.lsda.plantilla.ATLAS_CHAPINERO;
import com.lsda.plantilla.DINAMICA_ESTRATEGIA_Mimos;
import com.lsda.plantilla.GLORIA_TERESA_RIOS_SALAMANCA_Mimos;
import com.lsda.plantilla.CANAVERAL_PLAZA_Mimos;
import com.lsda.plantilla.CAROLINA_CASTANO_SANIN_Mimos;
import com.lsda.plantilla.DISTRIBUCIONES_UNIDAS_ORIENTE_Mimos;
import com.lsda.plantilla.DISTRIBUCIONES_SAROGA_Mimos;
import com.lsda.plantilla.FOODCARE_Mimos;
import com.lsda.plantilla.HOTEL_COMFORT_REINA_ISABELLA_Mimos;
import com.lsda.plantilla.INTEGRAR_SALUD_SEGURIDAD_Mimos;
import com.lsda.plantilla.INVERSIONES_CRIVASI_Mimos;
import com.lsda.plantilla.INVERSIONES_FONAR_Mimos;
import com.lsda.plantilla.INVERSIONES_NARANJO_MENESES_Mimos;
import com.lsda.plantilla.INVERSIONES_NARANJO_SANCHEZ_Mimos;
import com.lsda.plantilla.INVERSIONES_RUBIANO_AGUILAR_Mimos;
import com.lsda.plantilla.JAIME_DAVID_ALVAREZ_ROMERO_Mimos;
import com.lsda.plantilla.JIRETH_HELADOS_Mimos;
import com.lsda.plantilla.LINA_MARIA_VARGAS_ARIAS_Mimos;
import com.lsda.plantilla.LUZ_MERY_JARAMILLO__CARDENAS_Mimos;
import com.lsda.plantilla.MIDELIFOOD_Mimos;
import com.lsda.plantilla.OSWALDO_PACHECO_Mimos;
import com.lsda.plantilla.PERSEO_Mimos;
import com.lsda.plantilla.RODEO_PLAZA_DISTRIBUCIONES_Mimos;
import com.lsda.plantilla.SANDRA_PATRICIA_NOGUERA_CELY_Mimos;
import com.lsda.plantilla.LINA_MARIA_MESA_LOPERA_Mimos;
import com.lsda.plantilla.GRUPO_EMPRESARIAL_VASANT_Mimos;
import com.lsda.plantilla.YASMIN_IVON_DIEZ_GIRALDO_Mimos;
import com.lsda.plantilla.INVERSIONES_SGA_Mimos;
import com.lsda.plantilla.SALDA2_Mimos;
import com.lsda.plantilla.FROZEN_ICE_CREAM_INVERSIONES_Mimos;
import com.lsda.plantilla.COMERCIALIZADORA_KHALOZ_Mimos;
import com.lsda.plantilla.MARCA_MM_Mimos;
import com.lsda.plantilla.DISTRIBUCIONES_BARRANCABERMEJA_Mimos;
import com.lsda.plantilla.JUAN_DAVID_MARIN_OSPINA_Mimos;
import com.lsda.plantilla.LUIS_FELIPE_SALDARRIAGA_BURGOS_Mimos;
import com.lsda.plantilla.SOLUCIONES_EMPRESARIALES_INTEGRAR_Mimos;
import com.lsda.plantilla.ALIANZA_ESTRATEGICA_PUBLICIDAD_Mimos;
import com.lsda.plantilla.INVERSIONES_SHALOM_CIA_Mimos;
import com.lsda.plantilla.GRUPO_AZOTEA_ZorroAzul;
import com.lsda.plantilla.COMERCIAL_BM_Mimos;
import com.lsda.plantilla.INVERSIONES_TORRES_ALMARIO_Mimos;
import com.lsda.plantilla.INVERSIONES_ANY_Mimos;
import com.lsda.plantilla.SyT_SOLUTIONS_Mimos;
import com.lsda.plantilla.MEAL_SERVICES_AmorAlTaco;
import com.lsda.plantilla.GALIA_ALIMENTOS_Steakhouse;
import com.lsda.plantilla.Osaka;
import com.lsda.plantilla.SHOW_ME_THE_MONEY_Andres;
import com.lsda.plantilla.OPEN_WORLD_EXP_ClinkLicores;
import com.lsda.plantilla.FABRICA_ALIMENTOS_PROCESADOS_VENTOLINI;
import com.lsda.plantilla.RESTAURANTE_VIOLETAS;
import com.lsda.plantilla.COMPANIA_COMERCIAL_INDUSTRIAL_SABANA_AVESCO_Kokoriko;
import com.lsda.plantilla.BEATRIZ_VALENCIA_AYALA_Mimos;
import com.lsda.plantilla.INVERSIONES_HSBS_Mimos;
import com.lsda.plantilla.GRUPO_TERRAZA_PANCE;
import com.lsda.plantilla.AVINCO_Kokoriko;
import com.lsda.plantilla.PJ_COL_PapaJohns;
import com.lsda.plantilla.Delimex_Aldelo;
import com.lsda.plantilla.EL_MANA_CMA_Mimos;
import com.lsda.plantilla.Croissantina_Aldelo;
import com.lsda.plantilla.DonTelmo_Aldelo;
import com.lsda.plantilla.SAN_JERONIMO_CAJICA;
import com.lsda.plantilla.AMALSA_INVERSIONES_Aldelo;
import com.lsda.plantilla.DLK_Aldelo;
import com.lsda.plantilla.Servicios_Equipos_Mimos;
import com.lsda.plantilla.SMILE_BOGOTA_Aldelo;
import com.lsda.plantilla.GRUPO_LALALA_Aldelo;
import com.lsda.plantilla.MOLA_GROUP_Aldelo;
import com.lsda.plantilla.DISTRIBUIDORA_BARRANQUILLITA_Mimos;
import com.lsda.plantilla.GRUPO_OK_Aldelo;
import com.lsda.plantilla.CineColombia;
import com.lsda.plantilla.Orso_Aldelo;
import com.lsda.plantilla.JOSE_DANIEL_MONTERO_ERAZO_Mimos;
import com.lsda.plantilla.OrsoParkway_Aldelo;
import com.lsda.plantilla.MERITARE_Orso_Aldelo;
import com.lsda.plantilla.HELADOSVyB_Mimos;
import com.lsda.plantilla.RESTAURANTE_MI_MARGARITA_CAMPESTRE;
import com.lsda.plantilla.COMERCIALIZADORA_RUMBOS_Aldelo;
import com.lsda.plantilla.PastaPronta_Aldelo;
import com.lsda.plantilla.SOLUCIONES_MACAL_Mimos;
import com.lsda.plantilla.IRCC_BurgerBar_Corral;
import com.lsda.plantilla.THE_GASTROBAR_BROOKLYN;
import com.lsda.plantilla.AALTO_BISTRO;
import com.lsda.plantilla.SyT_SOLUTIONS_Mimos;
import com.lsda.plantilla.MANTO_DEL_MAR_Mimos;
import com.lsda.plantilla.GREEN_Dominos;
import com.lsda.plantilla.GRUPO_PEZETARIAN_Aldelo;
import com.lsda.plantilla.DGS;
import com.lsda.plantilla.OrsoMovil_Aldelo;
import com.lsda.plantilla.GRUPO_OCASO_Aldelo;
import com.google.zxing.WriterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import javax.naming.ConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author LSDA
 */
public class GeneracionPDF {

    private String qrdata;
    private String cufe;
    private Encabezado enc;
    private List<Detalle> detF;
    private List<Impuesto> impF;
    private List<Cargo> carF;
    private String xmlSellado;

    private EmpresaRepository empresaRepo;
    private FacturaRepository facRepo;
    private ResolucionesRepository resolucionRepo;

    /*
    public GeneracionPDF(String xmlSellado, String qrdata, String cufe, Encabezado enc, List<Detalle> detF, List<Impuesto> impF, List<Cargo> carF, EmpresaRepository empresaRepo, ResolucionesRepository resolucionRepo) {
        this.xmlSellado = xmlSellado;
        this.qrdata = qrdata;
        this.cufe = cufe;
        this.enc = enc;
        this.detF = detF;
        this.impF = impF;
        this.carF = carF;
        this.empresaRepo = empresaRepo;
        this.resolucionRepo = resolucionRepo;

    }*/
    public GeneracionPDF(String qrdata, String cufe, Encabezado enc, List<Detalle> detF, List<Impuesto> impF, List<Cargo> carF, String xmlSellado, EmpresaRepository empresaRepo, ResolucionesRepository resolucionRepo) {
        this.qrdata = qrdata;
        this.cufe = cufe;
        this.enc = enc;
        this.detF = detF;
        this.impF = impF;
        this.carF = carF;
        this.xmlSellado = xmlSellado;
        this.empresaRepo = empresaRepo;
        this.resolucionRepo = resolucionRepo;
    }

    private String convertirPdfABase64(File filePdf) {
        Base64.Encoder base64 = Base64.getEncoder();

        byte[] fileArray = new byte[(int) filePdf.length()];
        InputStream inputStream;

        String encodedFile = "";
        try {
            inputStream = new FileInputStream(filePdf);
            inputStream.read(fileArray);
            encodedFile = base64.encodeToString(fileArray);
        } catch (Exception e) {
            // Manejar Error
        }
        return encodedFile;

    }

    public String generarFilePDF() throws IOException, ConfigurationException, WriterException, ParseException, Exception {
        File archivoPDF = generarPDF(this.enc, this.detF, this.impF, this.carF);
        if (archivoPDF != null) {
            return convertirPdfABase64(archivoPDF);
        }
        return "";

    }

    private void cargarValores(CreacionRepresentacion representacion, Empresa empresa, Resoluciones resolucion, String nombreJasper, Encabezado encabezado, List<Detalle> detalles, List<Impuesto> impuestos, List<Cargo> cargos) {
        representacion.setEncabezado(encabezado);
        representacion.setDetalles(detalles);
        representacion.setImpuestos(impuestos);
        representacion.setCargos(cargos);
        representacion.setNombreJasper(nombreJasper);
        representacion.setCufe(cufe);
        representacion.setQrdata(qrdata);
        representacion.setXmlSellado(xmlSellado);
        representacion.setEmpresa(empresa);
        representacion.setResolucion(resolucion);

    }

    private File generarPDF(Encabezado encabezado, List<Detalle> detalles, List<Impuesto> impuestos, List<Cargo> cargos) throws IOException, ConfigurationException, WriterException, ParseException, SAXException, Exception {

        CreacionRepresentacion representacion = null;
        String nombreJasper;

        //fecha validacion
        // String FechaValida = "";
        // UtileriasPDF UtilPdf = new UtileriasPDF();
        //  Factura fac = facRepo.getFactura(cufe);
        // FechaValida = UtilPdf.cargarFEchaValida(fac.getAppres().toString());
        //Fin fecha validacion  
        Empresa empresa = empresaRepo.getEmpresa(encabezado.getNumeroDocumentoEmisor());
        if (empresa == null) {
            return null;
        } else {
            //System.out.println("JASPER: " + encabezado.getJasper());
            /*if(encabezado.getJasper().equals("")){
                System.out.println("ENTRO hdsds");
                encabezado.setJasper("1");
            }else if(encabezado.getJasper().equals("2")){
                System.out.println("ENTRO hdsds");
                encabezado.setJasper("5");
            }*/
            String prefijoLimpio = encabezado.getPrefijo().replaceAll("[\\p{Cntrl}]", "");
            String resolucionLimpia = encabezado.getNoResolucion().replaceAll("[\\p{Cntrl}]", "");
            Resoluciones resolucion = resolucionRepo.getResolucion(resolucionLimpia, empresa.getId(), prefijoLimpio);
            nombreJasper = encabezado.getJasper() + "_" + encabezado.getNumeroDocumentoEmisor() + ".jasper";
            switch (empresa.getNit()) {

                case "900934851": //Gastronomía Italiana En Colombia S.A.S
                    representacion = new GastronomiaItaliana();
                    break;
                case "900632938": //Estrella Andina S.A.S
                    representacion = new EstrellaAndina();
                    break;

                case "860076919": // CrepesWaffles
                    representacion = new CrepesWaffles();
                    break;

                case "800070655": // Maquite
                    representacion = new Maquite();
                    break;
                case "802006391": // CREPES CARIBE S.A.
                    representacion = new CREPES_CARIBE();
                    break;
                case "806014415": // CREPES CARTAGENA S.A.
                    representacion = new CREPES_CARTAGENA();
                    break;
                case "830092061": // HARD ROCK
                    representacion = new HRC();
                    break;
                case "900477874": // Inversiones ANTICUARIOS Bandido
                    representacion = new BandidoBristro();
                    break;
                case "830027609": // Entrepues
                    representacion = new Entrepues();
                    break;
                case "800084279": // GIL Y BOHORQUEZ
                    representacion = new Pescadero_GIL();
                    break;
                case "901682309": // Hanks Hamburguesa
                    representacion = new Hanks_Hamburguesa();
                    break;
                case "900467169": // Herencia
                    representacion = new Herencia();
                    break;
                case "901466404": // LaCien
                    representacion = new LaCien();
                    break;
                case "830084562": // Matis Oliveto
                    representacion = new Oliveto();
                    break;
                case "901093159": // LPQ
                    representacion = new LPQ();
                    break;
                case "860350253": // INMACULADA_GUADALUPE Andres DC
                    representacion = new INMACULADA_GUADALUPE();
                    break;
                case "901478431": // GAU FOODS S.A.S - Godo
                    representacion = new GauFoods();
                    break;
                case "901194172": // FOODINC SAS - Brera
                    representacion = new FoodInc();
                    break;
                case "900249607": // OPERADORA DE FRANQUICIAS DE COLOMBIA S A S BUFFALO
                    representacion = new OperadorFranquiciasCol();
                    break;
                case "900341322": // METRO OPERACION INMOBILIARIA S.A.S
                    representacion = new METRO_OPERACION_INMOBILIARIA();
                    break;
                case "901566837": // Dondoh
                    representacion = new Dondoh();
                    break;
                case "900656182": // METRO OPERACION INMOBILIARIA S.A.S
                    representacion = new CartelSushi();
                    break;
                case "900392211": // CAFE DE SANTA BARBARA S.A.S. PERGAMINO
                    representacion = new CAFE_SANTA_BARBARA();
                    break;
                case "901150256": // KOE4K S.A.S -- KO
                    representacion = new KoAsian();
                    break;
                case "901115013": // MONSTRUO SAS
                    representacion = new Monstruo();
                    break;
                case "900348317": // CUCINARE S.A.S - Salvators
                    representacion = new CUCINARE();
                    break;
                case "900190780": // G L 5 S.A.S. - La subienda
                    representacion = new GL5_LaSubienda();
                    break;
                case "832003298": // Pardo - La Huertana
                    representacion = new Pardo_LaHuertana();
                    break;
                case "900139289": // Abasto - NVERSIONES LA DESPENSA S A S
                    representacion = new Abasto();
                    break;
                case "901035244": // INSURGENTES S.A.S 
                    representacion = new Insurgentes();
                    break;
                case "901449687": // Pastor del 7 - NO SALGAS YA TE CHIFLO SAS
                    representacion = new Pastordel7();
                    break;
                case "901230769": // MERA MEDELLIN S.A.S
                    representacion = new Mera_Medellin();
                    break;
                case "900428464": // Mercari - MEDIOS Y PROGRAMAS S A S
                    representacion = new Mercari();
                    break;
                case "900808926": // Home Burgers - COMIDAS VARPEL SAS
                    representacion = new HomeBurgers();
                    break;
                case "901541469": // FLORA CHAPINERO SAS
                    representacion = new FloraChapinero();
                    break;
                case "901092340": // AX2 GOURMET SAS - La Lucha
                    representacion = new Ax2Gourmet();
                    break;
                case "901009488": // MERA EL DORADO S.A.S.
                    representacion = new Mera_Dorado();
                    break;
                case "830129394": // SPOLETO CULINARIA ITALIANA S A S
                    representacion = new Spoleto();
                    break;
                case "901203371": // VILLA DE NUESTRA SEÑORA DE LA CANDELARIA - Andrés
                    representacion = new VILLA_NUESTRA_SENORA_CANDELARIA();
                    break;
                case "830109677": // SU PRESENCIA PRODUCCIONES LTDA
                    representacion = new SU_PRESENCIA();
                    break;
                case "901095468": // BKAL S.A.S Burger King
                    representacion = new BKAL_Burger();
                    break;
                case "901095278": // BKCOL S.A.S Burger King
                    representacion = new BKCOL_Burger();
                    break;
                case "900103877": // KINCO Burger King
                    representacion = new KINCO_Burger();
                    break;
                case "890912221": // P.C.A. PRODUCTORA Y COMERCIALIZADORA DE ALIMENTOS S.A.S. Mimos
                    representacion = new PCA_Mimos();
                    break;
                case "900566365": // DOMIORIENTE SAS - Dominos
                    representacion = new DOMIORIENTE_Dominos();
                    break;
                case "901334517": // FOOD GROUP COLOMBIA S.A.S. - Dominos
                    representacion = new FOOD_GROUP_COLOMBIA_Dominos();
                    break;
                case "900697771": // GRUPO EMPRESARIAL PANAL SAS - Dominos
                    representacion = new GRUPO_EMPRESARIAL_PANAL_Dominos();
                    break;
                case "901256128": // R&B INVESTMENTS S.A.S. - Dominos
                    representacion = new RyB_INVESTMENTS_Dominos();
                    break;
                case "901291833": // INVERSIONES GASTRONOMICAS JASNABI SAS - Andres
                    representacion = new INVERSIONES_GASTRONOMICAS_JASNABI_Andres();
                    break;
                case "901285057": // LA DIVINA COMEDIA S.A.S. - Andres
                    representacion = new LA_DIVINA_COMEDIA_Andres();
                    break;
                case "900394207": // LOS AMIGOS DEL 221 S.A.S. - Andres
                    representacion = new LOS_AMIGOS_DEL_221_Andres();
                    break;
                case "901788358": // RESTAURANTE Y EVENTOS MI MARGARITA SAS
                    representacion = new RESTAURANTE_EVENTOS_MI_MARGARITA();
                    break;
                case "901260035": // DARI FROST HELADOS DE COLOMBIA S.A.S.
                    representacion = new DARI_FROST_HELADOS_COLOMBIA();
                    break;
                case "900094986": // MATUNA INVERSIONES S.A.S
                    representacion = new MATUNA_INVERSIONES_Gaira();
                    break;
                  case "900120082": // THE MONKEY HOUSE S A S
                    representacion = new THE_MONKEY_HOUSE();
                    break;
                case "800098726": // INVERSIONES JUANFE S.A.S. - Kokoriko
                    representacion = new INVERSIONES_JUANFE_Kokoriko();
                    break;
                case "901420919": // ATLAS CHAPINERO SAS
                    representacion = new ATLAS_CHAPINERO();
                    break;
                case "901369170": // DINÁMICA Y ESTRATEGIA S.A.S
                    representacion = new DINAMICA_ESTRATEGIA_Mimos();
                    break;
                case "34551936": // GLORIA TERESA RIOS SALAMANCA
                    representacion = new GLORIA_TERESA_RIOS_SALAMANCA_Mimos();
                    break;
                case "900052662": // CAÑAVERAL PLAZA LTDA
                    representacion = new CANAVERAL_PLAZA_Mimos();
                    break;
                case "30392056": // CAROLINA CASTAÑO SANIN
                    representacion = new CAROLINA_CASTANO_SANIN_Mimos();
                    break;
                case "901321442": // DISTRIBUCIONES UNIDAS DE ORIENTE SAS
                    representacion = new DISTRIBUCIONES_UNIDAS_ORIENTE_Mimos();
                    break;
                case "900550665": // DISTRIBUCIONES SAROGA S.A.S.
                    representacion = new DISTRIBUCIONES_SAROGA_Mimos();
                    break;
                case "900518011": // FOODCARE S.A.S.
                    representacion = new FOODCARE_Mimos();
                    break;
                case "901180200": // HOTEL COMFORT REINA ISABELLA S.A.S.
                    representacion = new HOTEL_COMFORT_REINA_ISABELLA_Mimos();
                    break;
                case "901200839": // INTEGRAR SALUD Y SEGURIDAD S.A.S
                    representacion = new INTEGRAR_SALUD_SEGURIDAD_Mimos();
                    break;
                case "800219130": // INVERSIONES CRIVASI SAS
                    representacion = new INVERSIONES_CRIVASI_Mimos();
                    break;
                case "901296291": // INVERSIONES FONARS.A.S.
                    representacion = new INVERSIONES_FONAR_Mimos();
                    break;
                case "900160034": // INVERSIONES NARANJO MENESES S.A.S.
                    representacion = new INVERSIONES_NARANJO_MENESES_Mimos();
                    break;
                case "900533947": // INVERSIONES NARANJO SANCHEZ S.A.S.
                    representacion = new INVERSIONES_NARANJO_SANCHEZ_Mimos();
                    break;
                case "901748319": // INVERSIONES RUBIANO AGUILAR SAS
                    representacion = new INVERSIONES_RUBIANO_AGUILAR_Mimos();
                    break;
                case "92501373": // JAIME DAVID ALVAREZ ROMERO
                    representacion = new JAIME_DAVID_ALVAREZ_ROMERO_Mimos();
                    break;
                case "901006382": // JIRETH HELADOS SAS
                    representacion = new JIRETH_HELADOS_Mimos();
                    break;
                case "30292405": // LINA MARIA VARGAS ARIAS
                    representacion = new LINA_MARIA_VARGAS_ARIAS_Mimos();
                    break;
                case "43010358": // LUZ MERY JARAMILLO CARDENAS
                    representacion = new LUZ_MERY_JARAMILLO__CARDENAS_Mimos();
                    break;
                case "900641079": // MIDELIFOOD S.A.S
                    representacion = new MIDELIFOOD_Mimos();
                    break;                    
                case "900689860": // OSWALDO PACHECO Y CIA. S.A.S.
                    representacion = new OSWALDO_PACHECO_Mimos();
                    break;
                case "811044046": // PERSEO S.A.S.
                    representacion = new PERSEO_Mimos();
                    break;
                case "900041150": // RODEO PLAZA DISTRIBUCIONES E.U.
                    representacion = new RODEO_PLAZA_DISTRIBUCIONES_Mimos();
                    break;
                case "52019319": // SANDRA PATRICIA NOGUERA CELY
                    representacion = new SANDRA_PATRICIA_NOGUERA_CELY_Mimos();
                    break;
                case "43626858": // LINA MARIA MESA LOPERA
                    representacion = new LINA_MARIA_MESA_LOPERA_Mimos();
                    break;
                case "901706795": // GRUPO EMPRESARIAL VASANT SAS
                    representacion = new GRUPO_EMPRESARIAL_VASANT_Mimos();
                    break;
                case "66825018": // YASMIN IVON DIEZ GIRALDO
                    representacion = new YASMIN_IVON_DIEZ_GIRALDO_Mimos();
                    break;
                case "900810209": // INVERSIONES SGA SAS
                    representacion = new INVERSIONES_SGA_Mimos();
                    break;
                case "901094268": // SALDA2 S.A.S.
                    representacion = new SALDA2_Mimos();
                    break;
                case "900622459": // FROZEN ICE CREAM INVERSIONES S.A.S.
                    representacion = new FROZEN_ICE_CREAM_INVERSIONES_Mimos();
                    break;
                case "901725217": // COMERCIALIZADORA KHALOZ S.A.S.
                    representacion = new COMERCIALIZADORA_KHALOZ_Mimos();
                    break;
                case "811040617": // MARCA MM S.A.S
                    representacion = new MARCA_MM_Mimos();
                    break;
                case "900322409": // DISTRIBUCIONES BARRANCABERMEJA REAL LTDA
                    representacion = new DISTRIBUCIONES_BARRANCABERMEJA_Mimos();
                    break;
                case "1036958836": // JUAN DAVID MARIN OSPINA
                    representacion = new JUAN_DAVID_MARIN_OSPINA_Mimos();
                    break;
                case "98587358": // LUIS FELIPE SALDARRIAGA BURGOS
                    representacion = new LUIS_FELIPE_SALDARRIAGA_BURGOS_Mimos();
                    break;
                case "901367103": // SOLUCIONES EMPRESARIALES INTEGRAR SAS
                    representacion = new SOLUCIONES_EMPRESARIALES_INTEGRAR_Mimos();
                    break;
                case "900218148": // ALIANZA ESTRATEGICA PUBLICIDAD S.A.S
                    representacion = new ALIANZA_ESTRATEGICA_PUBLICIDAD_Mimos();
                    break;
                case "901228930": // INVERSIONES SHALOM Y CIA S.A.S.
                    representacion = new INVERSIONES_SHALOM_CIA_Mimos();
                    break;
                case "901324042": // GRUPO LA AZOTEA S.A.S.
                    representacion = new GRUPO_AZOTEA_ZorroAzul();
                    break;
                case "901474064": // COMERCIAL BM SAS
                    representacion = new COMERCIAL_BM_Mimos();
                    break;
                case "900147047": // INVERSIONES TORRES ALMARIO S.A.S.
                    representacion = new INVERSIONES_TORRES_ALMARIO_Mimos();
                    break;
                case "901582127": // INVERSIONES ANY SAS
                    representacion = new INVERSIONES_ANY_Mimos();
                    break;
                case "900504796": // S&T SOLUTIONS SAS
                    representacion = new SyT_SOLUTIONS_Mimos();
                    break;
                case "901606853": // MEAL SERVICES SAS - Amor al Taco
                    representacion = new MEAL_SERVICES_AmorAlTaco();
                    break;
                case "900272717": // GALIA ALIMENTOS S.A.S - Steakhouse
                    representacion = new GALIA_ALIMENTOS_Steakhouse();
                    break;
                case "900917436": // OSAKA COCINA NIKKEI S.A.S.
                    representacion = new Osaka();
                    break;
                case "901336838": // SHOW ME THE MONEY S.A.S
                    representacion = new SHOW_ME_THE_MONEY_Andres ();
                    break;
                case "901423108": // OPEN WORLD EXP SAS - Clink Licores
                    representacion = new OPEN_WORLD_EXP_ClinkLicores();
                    break;
                case "800065567": // FABRICA DE ALIMENTOS PROCESADOS VENTOLINI S.A. EN REORGANIZACION
                    representacion = new FABRICA_ALIMENTOS_PROCESADOS_VENTOLINI();
                    break;
                case "832005245": // RESTAURANTE PARRILLA BAR LAS VIOLETAS LIMITADA
                    representacion = new RESTAURANTE_VIOLETAS();
                    break;
                case "860025461": // COMPAÑIA COMERCIAL E INDUSTRIAL LA SABANA AVESCO S.A.S
                    representacion = new COMPANIA_COMERCIAL_INDUSTRIAL_SABANA_AVESCO_Kokoriko();
                    break;
                case "41923162": // BEATRIZ VALENCIA AYALA
                    representacion = new BEATRIZ_VALENCIA_AYALA_Mimos();
                    break;
                case "901820537": // INVERSIONES HSBS S.A.S
                    representacion = new INVERSIONES_HSBS_Mimos();
                    break;
                case "901760419": // GRUPO TERRAZA PANCE S.A.S.
                    representacion = new GRUPO_TERRAZA_PANCE();
                    break;
                case "891401781": // AVINCO S . A . S .
                    representacion = new AVINCO_Kokoriko();
                    break;
                case "900328834": // P J COL S A S
                    representacion = new PJ_COL_PapaJohns();
                    break;
                case "900057199": // DELIMEX S.A.S.
                    representacion = new Delimex_Aldelo();
                    break;
                case "901846727": // EL MANA C.M.A SAS
                    representacion = new EL_MANA_CMA_Mimos();
                    break;
                case "901554773": // LA CROISSANTINA S.A.S
                    representacion = new Croissantina_Aldelo();
                    break;
                case "901755043": // DON TELMO S.A.S
                    representacion = new DonTelmo_Aldelo();
                    break;
                case "900292343": // SAN JERONIMO CAJICA INVERSIONES S.A.S
                    representacion = new SAN_JERONIMO_CAJICA();
                    break;
                case "901309982": // AMALSA INVERSIONES SAS
                    representacion = new AMALSA_INVERSIONES_Aldelo();
                    break;
                 case "800095036": // D L K S.A.S.
                    representacion = new DLK_Aldelo();
                    break;
                 case "819005931": // SERVICIOS Y EQUIPOS SAS
                    representacion = new Servicios_Equipos_Mimos();
                    break;
                 case "901347546": // SMILE BOGOTÁ SAS
                    representacion = new SMILE_BOGOTA_Aldelo();
                    break;
                 case "901163871": // GRUPO LA LA LA S.A.S.
                    representacion = new GRUPO_LALALA_Aldelo();
                    break;
                 case "901876859": // MOLA GROUP S.A.S
                    representacion = new MOLA_GROUP_Aldelo();
                    break;
                 case "901215644": // DISTRIBUIDORA BARRANQUILLITA S.A.S. ZESE
                    representacion = new DISTRIBUIDORA_BARRANQUILLITA_Mimos();
                    break;
                 case "900452021": // GRUPO OK S.A.S.
                    representacion = new GRUPO_OK_Aldelo();
                    break;
                 case "890900076": // CINE COLOMBIA S.A.S.
                    representacion = new CineColombia();
                    break;
                 case "900846368": // ORSO SAS
                    representacion = new Orso_Aldelo();
                    break;
                 case "1193035333": // JOSE DANIEL MONTERO ERAZO
                    representacion = new JOSE_DANIEL_MONTERO_ERAZO_Mimos();
                    break;
                 case "901813655": // ORSO PARKWAY SAS
                    representacion = new OrsoParkway_Aldelo();
                    break;
                 case "901676065": // MERITARE SAS
                    representacion = new MERITARE_Orso_Aldelo();
                    break;
                 case "901906225": // HELADOS V&B SAS
                    representacion = new HELADOSVyB_Mimos();
                    break;
                 case "901907643": // RESTAURANTE MI MARGARITA CAMPESTRE SAS
                    representacion = new RESTAURANTE_MI_MARGARITA_CAMPESTRE();
                    break;
                 case "830508167": // COMERCIALIZADORA RUMBOS S A S
                    representacion = new COMERCIALIZADORA_RUMBOS_Aldelo();
                    break;
                 case "800059080": // PASTA PRONTA S A S
                    representacion = new PastaPronta_Aldelo();
                    break;
                 case "901924935": // SOLUCIONES MACAL S.A.S
                    representacion = new SOLUCIONES_MACAL_Mimos();
                    break;
                 case "860533413": // I R C C S.A.S INDUSTRIA DE RESTAURANTES CASUALES S.A.S
                    representacion = new IRCC_BurgerBar_Corral();
                    break;
                 case "901850144": // THE GASTROBAR BROOKLYN SAS
                    representacion = new THE_GASTROBAR_BROOKLYN();
                    break;
                 case "901921762": // AALTO BISTRO SAS
                    representacion = new AALTO_BISTRO();
                    break;
                 case "901932456": // SYT SOLUTIONS ENERGY S.A.S
                    representacion = new SyT_SOLUTIONS_Mimos();
                    break;
                 case "901913346": // MANTO DEL MAR SAS
                    representacion = new MANTO_DEL_MAR_Mimos();
                    break;
                 case "900316197": // GREEN S A S
                    representacion = new GREEN_Dominos();
                    break;
                 case "901080451": // GRUPO PEZETARIAN S.A.S
                    representacion = new GRUPO_PEZETARIAN_Aldelo();
                    break;
                 case "901521130": // DGS SAS
                    representacion = new DGS();
                    break;
                 case "901847882": // ORSO MOVIL S.A.S
                    representacion = new OrsoMovil_Aldelo();
                    break;
                 case "901687308": // GRUPO OCASO Y MAS SAS
                    representacion = new GRUPO_OCASO_Aldelo();
                    break;
            }

            if (crearDirectorioPDF(encabezado.getNumeroDocumentoEmisor())) {
                System.out.println("Directorio PDF creado para nit: " + encabezado.getNumeroDocumentoEmisor());
            } else {
                System.out.println("No se creo el directorio PDF");
            }
            if (crearDirectorioImagenes(encabezado.getNumeroDocumentoEmisor())) {
                System.out.println("Directorio Imagenes creado para nit: " + encabezado.getNumeroDocumentoEmisor());
            } else {
                System.out.println("No se creo el directorio imagenes");
            }
            //System.out.println("Nombre Jasper: "+nombreJasper);
            cargarValores(representacion, empresa, resolucion, nombreJasper, encabezado, detalles, impuestos, cargos);
            representacion.crearRepresentacionGrafica();

            return representacion.getPdfFile();
        }
    }

    private boolean crearDirectorioPDF(String numeroDocumentoEmisor) {
        System.out.println("Ruta a crear: " + RUTA_BASE_PDF + numeroDocumentoEmisor);
        return new File(RUTA_BASE_PDF + numeroDocumentoEmisor).mkdir();
    }

    private boolean crearDirectorioImagenes(String numeroDocumentoEmisor) {
        System.out.println("Ruta a crear: " + RUTA_BASE_IMAGENES + numeroDocumentoEmisor);
        return new File(RUTA_BASE_IMAGENES + numeroDocumentoEmisor).mkdir();
    }

}
