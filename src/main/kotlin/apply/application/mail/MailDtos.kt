package apply.application.mail

import apply.domain.mail.MailMessage
import apply.domain.mail.MailReservation
import apply.domain.mail.MailReservationStatus
import java.time.LocalDateTime

data class MailMessageResponse(
    val subject: String,
    val body: String,
    val sender: String,
    val recipients: List<String>,
    val createdDateTime: LocalDateTime,
    val reservation: MailReservationSimpleResponse?,
    val id: Long
) {
    constructor(mailMessage: MailMessage) : this(
        mailMessage.subject,
        mailMessage.body,
        mailMessage.sender,
        mailMessage.recipients,
        mailMessage.createdDateTime,
        mailMessage.reservation()?.let { MailReservationSimpleResponse(it) },
        mailMessage.id
    )
}

data class MailReservationSimpleResponse(
    val status: MailReservationStatus,
    val reservationTime: LocalDateTime,
    val id: Long,
) {
    constructor(mailReservation: MailReservation) : this(
        mailReservation.status,
        mailReservation.reservationTime,
        mailReservation.id
    )
}

data class MailReservationResponse(
    val mailMessage: MailMessageResponse,
    val status: MailReservationStatus,
    val reservationTime: LocalDateTime,
    val id: Long,
) {
    constructor(mailReservation: MailReservation) : this(
        MailMessageResponse(mailReservation.mailMessage),
        mailReservation.status,
        mailReservation.reservationTime,
        mailReservation.id
    )
}
