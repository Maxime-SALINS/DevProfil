package com.example.devprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.devprofil.ui.theme.DevProfilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevProfilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfil(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EcranProfil(modifier: Modifier = Modifier) {
    FicheProfil(
        nom = "Maxime Salins",
        role = "Étudiant développeur",
        description = "Étudiant en informatique, à l'aise en PHP, " +
                "qui découvre le développement Android avec Jetpack Compose.",
        competences = listOf("PHP", "Kotlin", "Jetpack Compose", "Git"),
        disponibilite = "Disponible pour un stage",
        contact = "maxime.salins@gmail.com",
        texteBouton = "Contacter",
        onActionClick = { /* Le composant signale le clic, aucune logique ici */ },
        onDisponibiliteClick = { /* Idem : simple callback */ },
        // Padding EXTERNE : le parent décide de la place de la fiche dans l'écran
        modifier = modifier.padding(16.dp)
    )
}
@Composable
fun FicheProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    disponibilite: String,
    contact: String,
    texteBouton: String,
    onActionClick: () -> Unit,
    onDisponibiliteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            EnteteProfil(nom = nom, role = role)

            Text(
                text = description,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
            for (competence in competences) {
                CompetenceItem(competence = competence)
            }

            ZoneDisponibilite(
                texte = disponibilite,
                onClick = onDisponibiliteClick,
                modifier = Modifier.padding(top = 16.dp)
            )

            InfoSecondaire(
                texte = "Contact : $contact",
                modifier = Modifier.padding(top = 8.dp)
            )

            BoutonAction(
                texte = texteBouton,
                onClick = onActionClick,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun EnteteProfil(nom: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = nom,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CompetenceItem(competence: String, modifier: Modifier = Modifier) {
    Text(
        text = "• $competence",
        modifier = modifier.padding(vertical = 2.dp)
    )
}

@Composable
fun InfoSecondaire(texte: String, modifier: Modifier = Modifier) {
    Text(
        text = texte,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun ZoneDisponibilite(
    texte: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        // Chaîne de modifiers : largeur -> clic -> sémantique
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = "$texte. Appuyer pour voir les détails"
            },
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = texte,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(12.dp) // padding interne de la zone
        )
    }
}

@Composable
fun BoutonAction(
    texte: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = texte)
    }
}

// Previews : deux profils différents
@Preview(showBackground = true, name = "Android junior - disponible")
@Composable
fun ProfilAndroidJuniorPreview() {
    DevProfilTheme {
        FicheProfil(
            nom = "Maxime Salins",
            role = "Développeur Android junior",
            description = "Étudiant motivé, je découvre Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            disponibilite = "Disponible pour un stage",
            contact = "maxime.salins@gmail.com",
            texteBouton = "Contacter",
            onActionClick = {},
            onDisponibiliteClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Full-stack - non disponible")
@Composable
fun ProfilFullStackPreview() {
    DevProfilTheme {
        FicheProfil(
            nom = "Camille Martin",
            role = "Développeuse full-stack",
            description = "Cinq ans d'expérience sur des applications web et mobiles.",
            competences = listOf("PHP", "JavaScript", "SQL", "Docker", "API REST"),
            disponibilite = "Indisponible jusqu'en janvier",
            contact = "camille.martin@gmail.com",
            texteBouton = "Voir le portfolio",
            onActionClick = {},
            onDisponibiliteClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}