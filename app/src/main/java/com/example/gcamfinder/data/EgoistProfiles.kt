package com.example.gcamfinder.data

data class EgoistDownload(
    val googleDriveFileId: String,
    val fileName: String,
    val sha256: String? = null
) {
    val isConfigured: Boolean
        get() = googleDriveFileId.isNotBlank()

    val directDownloadUrl: String
        get() = "https://drive.usercontent.google.com/download?id=$googleDriveFileId&export=download&confirm=t"
}

data class EgoistProfile(
    val deviceId: String,
    val displayName: String,
    val apk: EgoistDownload,
    val config: EgoistDownload,
    val library: EgoistDownload,
    val tutorialGoogleDriveFileId: String
) {
    val isConfigured: Boolean
        get() = apk.isConfigured &&
            config.isConfigured &&
            library.isConfigured &&
            tutorialGoogleDriveFileId.isNotBlank()
}

object EgoistProfiles {
    private val profiles = listOf(
        EgoistProfile(
            deviceId = "vivo_x300_pro",
            displayName = "VIVO X300 Pro",
            apk = EgoistDownload(
                googleDriveFileId = "1ClY5tXi03fRoDRBZigmR3aL-JsMqsqIi",
                fileName = "AGC8.4.300_V9.6_scan3d.apk",
                sha256 = "34c57b7ec5de3a2c7aed58b6ddb9c43b019d1cb593a66332d6240b2e9be8dbfd"
            ),
            config = EgoistDownload(
                googleDriveFileId = "1l6CDrl66ZF9khCYd9VSHXXgYzFWWAp6u",
                fileName = "EGOIST_1.2k16_X300P.agc",
                sha256 = "c2fdf41f7a18d30d4f754ccfc9365ab3cbcf7465e96bc622cb63af2d928a88d8"
            ),
            library = EgoistDownload(
                googleDriveFileId = "1nWk1EhhPTx42tubeTafVXgrH03OSWKVv",
                fileName = "shgv1.2k16.so",
                sha256 = "790e1c5e9ed023232295ddb51262b97ea7d81bba8a7645af4314791397e54ad9"
            ),
            tutorialGoogleDriveFileId = "1LyparpXDBuzbmQUIuzc0t96kQKNjmd7v"
        ),
        EgoistProfile(
            deviceId = "xiaomi_14_ultra",
            displayName = "XIAOMI 14 ULTRA",
            apk = EgoistDownload(
                googleDriveFileId = "1mhurWnjvNfU2B72k0y81aPWUunrIebTQ",
                fileName = "AGC8.4.300_V9.6_rulerX14U.apk",
                sha256 = "20291f408189d725654e351f1b8f574ea6d098d83f572584d61ae4df69949d83"
            ),
            config = EgoistDownload(
                googleDriveFileId = "1fckhgTQ4oPARif3DjQaNCzstTwBolQDT",
                fileName = "EGOIST_1.2k16_14u_12mp.agc",
                sha256 = "f0e304e0cb9699b1dc1c0a06cd154b2a05654eff6f2df14bc1b7a18b9b0c972a"
            ),
            library = EgoistDownload(
                googleDriveFileId = "1zSlMC_O0o4p9f7JV0t98en2OEN5cNtw9",
                fileName = "shgv1.2k16.so",
                sha256 = "790e1c5e9ed023232295ddb51262b97ea7d81bba8a7645af4314791397e54ad9"
            ),
            tutorialGoogleDriveFileId = "16TigGiJyM6K8Xk-r9TxObdriJQqRd_N2"
        ),
        EgoistProfile(
            deviceId = "xiaomi_15_ultra",
            displayName = "XIAOMI 15 ULTRA",
            apk = EgoistDownload(
                googleDriveFileId = "1UvDMDIDN4g1W43ulj7eO6e6JsYaZGbNk",
                fileName = "AGC8.4.300_V9.6_ruler15u.apk",
                sha256 = "20291f408189d725654e351f1b8f574ea6d098d83f572584d61ae4df69949d83"
            ),
            config = EgoistDownload(
                googleDriveFileId = "1L-mCdB9tzOuv6EbJ68DcYl7oCYtXoirq",
                fileName = "EGOIST_1.2k16_15u_12mp.agc",
                sha256 = "78106854a69746d1b59e293044b3219d4afafc31bf89c547722378626cc5a16e"
            ),
            library = EgoistDownload(
                googleDriveFileId = "1nsGHwBzXGadCA_5sC9eVp0hfZXwirKk8",
                fileName = "shgv1.2k16.so",
                sha256 = "790e1c5e9ed023232295ddb51262b97ea7d81bba8a7645af4314791397e54ad9"
            ),
            tutorialGoogleDriveFileId = "1Ps5UeSzNfSjvq14P6oLlMyqU7ZQQa0J0"
        ),
        EgoistProfile(
            deviceId = "xiaomi_17_ultra",
            displayName = "XIAOMI 17 ULTRA",
            apk = EgoistDownload(
                googleDriveFileId = "1rnwiWUkrH2xsnHYTDc-XZUvJjrK6N0aK",
                fileName = "x17ultraegoist.apk",
                sha256 = "270696b0c583c819aa3ce9eb8c1963f8e374b0c2b9b0f7365ecc3b9099fa318f"
            ),
            config = EgoistDownload(
                googleDriveFileId = "17jeu6RVSWHnmWOfuqFfjeLKUHjOWsEtj",
                fileName = "EGOIST_1.2k16_17u_12mp.agc",
                sha256 = "d18df42636bc0bb0030390d8492e4d2de18668b0e77bc863486cafb034afaf27"
            ),
            library = EgoistDownload(
                googleDriveFileId = "1pYVxZX5IqJNsbEqCzsXbrzzQT5Bo4j3k",
                fileName = "shgv1.2k16.so",
                sha256 = "790e1c5e9ed023232295ddb51262b97ea7d81bba8a7645af4314791397e54ad9"
            ),
            tutorialGoogleDriveFileId = "1HvcttI_32n5yU1RUYsHGF-8-kTWZE8cM"
        ),
        EgoistProfile(
            deviceId = "samsung_s26_ultra",
            displayName = "SAMSUNG S26 ULTRA",
            apk = EgoistDownload(
                googleDriveFileId = "1FDu0HyNUE6CLsSk_PcvYrbFSTE8KzQvi",
                fileName = "AGC8.4.300_V9.6_ruler.apk",
                sha256 = "20291f408189d725654e351f1b8f574ea6d098d83f572584d61ae4df69949d83"
            ),
            config = EgoistDownload(
                googleDriveFileId = "1FxkfREsSPYV69k718FhAPn5jpS6s2vgY",
                fileName = "EGOISTv44betaAGC8.4v9.6_S26U_test.agc",
                sha256 = "01536354ecac6f1ceefd1e40bd5f0fa136f50b83a1c9ecff124643f508b3ff93"
            ),
            library = EgoistDownload(
                googleDriveFileId = "10R4poIIk3f14DUzNfrZreFnNdMS7sAxD",
                fileName = "shgv913.so",
                sha256 = "790e1c5e9ed023232295ddb51262b97ea7d81bba8a7645af4314791397e54ad9"
            ),
            tutorialGoogleDriveFileId = "128suL9NhlSxuw-7bC9SUs2Xc0qpMcZTt"
        )
    ).associateBy(EgoistProfile::deviceId)

    fun forDevice(deviceId: String): EgoistProfile? = profiles[deviceId]

    fun supports(deviceId: String): Boolean = profiles.containsKey(deviceId)
}
